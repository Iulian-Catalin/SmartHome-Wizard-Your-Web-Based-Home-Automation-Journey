package itemmanagement;

import db.DBItemList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class TimerItemServlet extends HttpServlet implements Runnable {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Runnable timerItemServlet = new TimerItemServlet();

        Thread myThread= new Thread(timerItemServlet);

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

    @Override
    public void run(){

    }

}