package usermanagement.db;

import usermanagement.User;

import java.sql.*;

public class DBUser {

    public boolean newUser(User u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";

            System.out.println("parola:"+PASSWORD);

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (username, password, newsletter) VALUES(?,?,?)");
            pSt.setString(1,u.getEmail());
            pSt.setString(2,u.getPwd());
            pSt.setBoolean(3,u.isNewsletter());



            // 3. executie
            int insert = pSt.executeUpdate();
            if(insert!=-1)
                isInserted=true;
            System.out.println(isInserted);

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted=false;

        }


        return isInserted;
    }

    public User login (String username, String password) {

        User u = null;
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
        final String USERNAME = "postgres";

        final String PASSWORD = "postgres";
        int id =-1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul



            PreparedStatement pSt = conn.prepareStatement("select id, username from users where username=? and password=?");

            pSt.setString(1,username);
            pSt.setString(2,password);


            // 3. executie
            ResultSet rs = pSt.executeQuery();




            // atata timp cat am randuri
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


    public static void main(String[] args) {
        DBUser dbuser= new DBUser();
        User u = new User("gfdghdf", "ererw", "wertewrt", true, true);
        boolean b = dbuser.newUser(u);
    }
}
