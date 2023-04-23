package itemmanagement;

import itemmanagement.MyItemList;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import db.DBItemList;

import java.io.IOException;


@WebServlet("/poweritem")
public class PowerItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id");
        int idDB = Integer.parseInt(req.getParameter("idDB"));
        boolean power = Boolean.parseBoolean(req.getParameter("power"));
        power = !power;

        MyItemList mfl = new MyItemList(idDB, power);
        DBItemList db = new DBItemList();
        db.PowerItem(mfl);
        resp.sendRedirect("listClientMenu.jsp");
    }
}