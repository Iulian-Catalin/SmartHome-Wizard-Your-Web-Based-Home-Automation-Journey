package itemmanagement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import db.DBItemList;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/additem")
public class AddItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id");
        String itemname = req.getParameter("itemname");
        int room = Integer.parseInt(req.getParameter("room"));
        int watts = Integer.parseInt(req.getParameter("watts"));
        if (o != null && itemname != null) {

            LocalDate ld = LocalDate.now();
            int iduser = (int) o;

            MyItemList mfl = new MyItemList(itemname, ld, iduser, room, watts);
            DBItemList db = new DBItemList();
            db.newItem(mfl);
        } else {
            error(resp, "Operation forbidden, user is not logged in or all details are not arriving to server.");
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