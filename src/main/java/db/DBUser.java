package db;

import usermanagement.User;

import java.sql.*;

public class DBUser {

    public static void main(String[] args) {
        DBUser dbuser = new DBUser();
        User u = new User("email", "confirmEmail", "pwd", "confirmPwd", true, true);
        boolean b = dbuser.newUser(u);
    }

    public boolean newUser(User u) {

        System.out.println(u);

        boolean isInserted = false;
        try {
            // 1. DB connection
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";

            System.out.println("parola:" + PASSWORD);

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. Statement prepare and insert
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (username, password, newsletter) VALUES(?,?,?)");
            pSt.setString(1, u.getEmail());
            pSt.setString(2, u.getPwd());
            pSt.setBoolean(3, u.isNewsletter());


            // 3. Execution
            int insert = pSt.executeUpdate();
            if (insert != -1)
                isInserted = true;
            System.out.println(isInserted);

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted = false;

        }


        return isInserted;
    }

    public User login(String username, String password) {

        User u = null;
        // 1. DB connection
        final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
        final String USERNAME = "postgres";

        final String PASSWORD = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. Statement prepare and verify

            PreparedStatement pSt = conn.prepareStatement("select id, username from users where username=? and password=?");

            pSt.setString(1, username);
            pSt.setString(2, password);


            // 3. Execution
            ResultSet rs = pSt.executeQuery();


            // 4. As long as entries exist
            while (rs.next()) {

                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("username"));

            }

            rs.close();
            pSt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return u;
    }
}
