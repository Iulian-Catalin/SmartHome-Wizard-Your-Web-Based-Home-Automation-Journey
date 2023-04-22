package usermanagement.db;

import usermanagement.MyItemList;

import java.sql.*;
import java.time.LocalDate;
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
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO myitemlist (itemname,itemdate,iduser,room,watts) VALUES(?,?,?,?,?)");
            pSt.setString(1,u.getItemName());

            Date date = Date.valueOf(u.getItemDate());
            pSt.setDate(2, date);

            pSt.setInt(3, u.getIduser());

            pSt.setInt(4, u.getRoom());

            pSt.setInt(5, u.getWatts());



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
            PreparedStatement pSt = conn.prepareStatement("select * from myitemlist where iduser=? and itemname like CONCAT( '%',?,'%') ORDER BY itemdate desc");

            pSt.setInt(1, idUser);
            pSt.setString(2, search);


            // 3. Execution
            ResultSet rs = pSt.executeQuery();




            // As long as entries exist
            while (rs.next()) {

                mfl = new MyItemList();
                mfl.setItemName(rs.getString("itemname"));
                Date dateFromDB = rs.getDate("itemdate");
                LocalDate localDate = dateFromDB.toLocalDate();
                mfl.setItemDate(localDate);
                mfl.setRoom(rs.getInt("room"));
                mfl.setWatts(rs.getInt("watts"));
                mfl.setOn(rs.getBoolean("on"));
                mfl.setIdDB(rs.getInt("id"));

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


    public static void main(String[] args) {

        DBItemList db = new DBItemList();

        List<MyItemList> l = db.getItemList(48,"");

        for(int i = 0;i<l.size();i++) {

            MyItemList mfl = l.get(i);

            System.out.println(mfl.toString()); // Just to test we get the right data from db
        }
    }
}
