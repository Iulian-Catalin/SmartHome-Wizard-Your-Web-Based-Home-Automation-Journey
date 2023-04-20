package usermanagement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usermanagement.db.DBUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/userManagement")
public class UserManagementServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action"); // Name as in the html form
        System.out.println("action is:" + action);
        boolean succes;
        if (action != null && action.equalsIgnoreCase("NEW")) {

            succes = newUser(req, resp);
            if (succes) {
                RequestDispatcher rd = req.getRequestDispatcher("newLogin.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                error(resp, "there is an error while trying to create this user, pls try again");
            }

            // If create is succesfully, go to log in, else stay here.


        } else if (action != null && action.equalsIgnoreCase("LOGIN")) {
            // Display
            succes = loginUser(req, resp);
            if (succes) // In
            {
                RequestDispatcher rd = req.getRequestDispatcher("listMyStuff.jsp");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("newLogin.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (action != null && action.equalsIgnoreCase("OUT")) {
            HttpSession s = req.getSession();
            s.invalidate();
            try {
                resp.sendRedirect("newLogin.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("nu a venit action, deci nu fac nimic ");
            error(resp, "erro on ui side");
        }

    }

    private boolean newUser(HttpServletRequest req, HttpServletResponse resp) {
        // Read data from browser email , confirmEmail, pwd, confirmPwd, accept, newsletter

        String email = req.getParameter("email");
        String confirmEmail = req.getParameter("confirmEmail");
        String pwd = req.getParameter("pwd");
        String confirmPwd = req.getParameter("confirmPwd");
        String accepthtml = req.getParameter("accept");
        String newsletterhtml = req.getParameter("newsletter");

        boolean accept;
        boolean offer = false;

        if (!patternMatches(email)) {
            error(resp, "Formatul e-mailului nu este corect !");
            return false;
        }
        if (!email.equals(confirmEmail)) {
            error(resp, "Email is not the same as confirm email");
            return false;
        }
        if (!pwd.equals(confirmPwd)) {
            error(resp, "Password is not the same as confirm password");
            return false;
        }
        if (accepthtml == null) {
            error(resp, "You must accept terms and conditions");
            return false;
        } else accept = true;

        if (newsletterhtml != null) offer = true;

        DBUser dbUser = new DBUser();
        User u = new User(email, confirmEmail, pwd, confirmPwd, accept, offer);
        boolean inserted = dbUser.newUser(u);


        return inserted;

    }

    private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) {
        User u;
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        boolean isLoggedIn = false;

        DBUser dbUser = new DBUser();
        u = dbUser.login(email, pwd);
        if (u != null) {
            HttpSession s = req.getSession();
            s.setAttribute("id", u.getId());
            s.setAttribute("email", u.getEmail());
            isLoggedIn = true;
        }
        return isLoggedIn;
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

    public static boolean patternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }
}
