package usermanagement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usermanagement.db.DBRoomList;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addroom")
public class AddRoomServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id");
        String roomname = req.getParameter("roomname");
        if (o != null && roomname != null) {

            int iduser = (int) o;

            MyRoomList mrl = new MyRoomList(roomname,iduser);
            DBRoomList db = new DBRoomList();
            db.newRoom(mrl);
        } else {
            error(resp, "Operation forbidden, user is not logged in or roomname is not arriving to server.");
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