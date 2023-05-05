package db;

import itemmanagement.MyItemList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBItemList {

    public boolean newItem(MyItemList u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. DB connection
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //  2. Statement prepare and insert
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO myitemlist (itemname,iduser,room,watts,qty) VALUES(?,?,?,?,?)");
            pSt.setString(1,u.getItemName());

//            pSt.setTimestamp(2, u.getItemTimeStamp());

            pSt.setInt(2, u.getIduser());

            pSt.setString(3, u.getRoomName());

            pSt.setInt(4, u.getWatts());

            pSt.setInt(5, u.getQuantity());



            // 3. Execution
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

    public List<MyItemList> getItemList(int idUser, String search) {

        MyItemList mfl;
        List<MyItemList> list = new ArrayList<>();
        // 1. DB connection
        final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
        final String USERNAME = "postgres";

        final String PASSWORD = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. DB quesry and object create
            PreparedStatement pSt = conn.prepareStatement("select * from myitemlist where iduser=? and itemname like CONCAT( '%',?,'%') ORDER BY id asc");

            pSt.setInt(1, idUser);
            pSt.setString(2, search);


            // 3. Execution
            ResultSet rs = pSt.executeQuery();


            // As long as entries exist
            while (rs.next()) {
                mfl = new MyItemList();
                mfl.setItemName(rs.getString("itemname").trim());
                mfl.setItemTimeStamp(rs.getTimestamp("itemdate"));
                mfl.setRoomName(rs.getString("room").trim());
                mfl.setWatts(rs.getInt("watts"));
                mfl.setPower(rs.getBoolean("power"));
                mfl.setIdDB(rs.getInt("id"));
                mfl.setQuantity(rs.getInt("qty"));
                mfl.setConsumption(rs.getFloat("consumption"));

                list.add(mfl);
            }

            rs.close();
            pSt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean powerItem(MyItemList u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. DB connection
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


                //  2. Statement prepare and insert
                PreparedStatement pSt = conn.prepareStatement("UPDATE myitemlist SET power=?,itemdate=?,consumption=? WHERE id=?");

                pSt.setBoolean(1, u.isPower());
                pSt.setInt(4, u.getIdDB());

                if (u.isPower()) {
                    pSt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                    MyItemList mfl = new MyItemList();
                    List<MyItemList> list = new ArrayList<>();
                    // 1. DB connection
                    try {
                        Class.forName("org.postgresql.Driver");
                        Connection conn2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                        // 2. DB quesry and object create
                        PreparedStatement pSt2 = conn.prepareStatement("select * from myitemlist where id=?");

                        pSt2.setInt(1, u.getIdDB());


                        // 3. Execution
                        ResultSet rs = pSt2.executeQuery();


                        // As long as entries exist
                        while (rs.next()) {

                            mfl.setConsumption(rs.getFloat("consumption"));

                        }

                        rs.close();
                        pSt2.close();
                        conn2.close();

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    pSt.setFloat(3, mfl.getConsumption());
                } else {
                    MyItemList mfl = new MyItemList();
                    List<MyItemList> list = new ArrayList<>();
                    // 1. DB connection
                    try {
                        Class.forName("org.postgresql.Driver");
                        Connection conn2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                        // 2. DB quesry and object create
                        PreparedStatement pSt2 = conn.prepareStatement("select * from myitemlist where id=?");

                        pSt2.setInt(1, u.getIdDB());


                        // 3. Execution
                        ResultSet rs = pSt2.executeQuery();


                        // As long as entries exist
                        while (rs.next()) {
                            mfl.setItemTimeStamp(rs.getTimestamp("itemdate"));
                            mfl.setConsumption(rs.getFloat("consumption"));
                            mfl.setQuantity(rs.getInt("qty"));
                            mfl.setWatts(rs.getInt("watts"));
                        }

                        rs.close();
                        pSt2.close();
                        conn2.close();

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Timestamp timestamp1 = mfl.getItemTimeStamp();
                    Timestamp timestamp2 = Timestamp.valueOf(LocalDateTime.now());

                    long milliseconds = timestamp2.getTime() - timestamp1.getTime() ;
                    double seconds = (double) ( milliseconds / 1000);
                    double hours = seconds / 3_600;
                    BigDecimal kwh = new BigDecimal(((mfl.getWatts() * hours) / 1_000)*mfl.getQuantity());
                    String suma = kwh.toString().substring(0,5);
                    float kwh2= Float.valueOf(suma) + mfl.getConsumption();
                    pSt.setTimestamp(2, null);
                    pSt.setFloat(3, kwh2);

                    System.out.println(kwh2);

                }


                // 3. Execution
                int insert = pSt.executeUpdate();
                if (insert != -1)
                    isInserted = true;
                System.out.println(isInserted);

                pSt.close();
                conn.close();
            } catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
                isInserted = false;

            }

        return isInserted;
    }
    public boolean deleteItem(MyItemList u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. DB connection
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //  2. Statement prepare and insert
            PreparedStatement pSt = conn.prepareStatement("Delete from myitemlist WHERE id=?");

            pSt.setInt(1, u.getIdDB());



            // 3. Execution
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

    public boolean updateQtyItem(MyItemList u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. DB connection
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //  2. Statement prepare and insert
            PreparedStatement pSt = conn.prepareStatement("UPDATE myitemlist SET qty=?  WHERE id=?");

            pSt.setInt(1, u.getQuantity());
            pSt.setInt(2, u.getIdDB());


            // 3. Execution
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

    public static void main(String[] args) {

        DBItemList db = new DBItemList();

        List<MyItemList> l = db.getItemList(48,"");

        for(int i = 0;i<l.size();i++) {

            MyItemList mfl = l.get(i);

            System.out.println(mfl.toString()); // Just to test we get the right data from db
        }
    }
}
