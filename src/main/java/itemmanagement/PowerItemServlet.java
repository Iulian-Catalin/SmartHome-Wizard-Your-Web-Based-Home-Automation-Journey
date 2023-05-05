package itemmanagement;

import db.DBItemList;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/poweritem")
public class PowerItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id");
        int idDB = Integer.parseInt(req.getParameter("idDB"));
        boolean power = Boolean.parseBoolean(req.getParameter("power"));
        boolean timer = Boolean.parseBoolean(req.getParameter("timer"));
        int idUser = (int) o;
        if (!timer) {
            power = !power;

            MyItemList mfl = new MyItemList(idDB, power);
            DBItemList db = new DBItemList();
            db.powerItem(mfl);
            resp.sendRedirect("listClientMenu.jsp");
        } else {
            power = true;

            MyItemList mfl = new MyItemList(idDB, power);
            DBItemList db = new DBItemList();
            db.powerItem(mfl);

            power = !power;
            mfl.setPower(power);
            Thread t2 = new Thread(() -> {
                try {
                    TimerItemClass.setTimer(mfl);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            t2.start();
            resp.sendRedirect("listClientMenu.jsp");


        }
    }
}