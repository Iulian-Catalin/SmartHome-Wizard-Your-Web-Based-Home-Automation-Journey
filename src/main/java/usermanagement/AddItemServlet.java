package usermanagement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usermanagement.db.DBItemList;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/additem")
public class AddItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii
        String itemname = req.getParameter("itemname");
        if (o != null && itemname != null) {

            LocalDate ld = LocalDate.now();
            int iduser = (int) o;

            MyItemList mfl = new MyItemList(itemname, ld, iduser);
            DBItemList db = new DBItemList();
            db.newItem(mfl);
        } else {
            error(resp, "operation forbidden. user is not logged in or itemname is not arriving to server");
        }
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

    private void error(HttpServletResponse resp, String mesaj) {

        try {
            PrintWriter pw = resp.getWriter();
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}