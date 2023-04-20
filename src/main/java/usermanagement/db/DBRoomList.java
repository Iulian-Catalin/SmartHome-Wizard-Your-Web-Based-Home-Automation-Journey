package usermanagement.db;

import usermanagement.MyRoomList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRoomList {

    public boolean newRoom(MyRoomList u) {

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
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO rooms (roomname, iduser) VALUES(?, ?)");
            pSt.setString(1,u.getRoomName());
            pSt.setInt(2, u.getIduser());


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

    public List<MyRoomList> getRoomList(int idUser, String search) {

        MyRoomList mrl;
        List<MyRoomList> list = new ArrayList<>();
        // 1. DB connection
        final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
        final String USERNAME = "postgres";

        final String PASSWORD = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. DB quesry and object create
            PreparedStatement pSt = conn.prepareStatement("select * from rooms where iduser=? and roomname like CONCAT( '%',?,'%') ORDER BY id asc");

            pSt.setInt(1, idUser);
            pSt.setString(2, search);


            // 3. Execution
            ResultSet rs = pSt.executeQuery();




            // As long as entries exist
            while (rs.next()) {

                mrl = new MyRoomList();
                mrl.setId(rs.getInt("id"));
                mrl.setRoomName(rs.getString("roomname"));

                list.add(mrl);
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

        DBRoomList db = new DBRoomList();

        List<MyRoomList> l = db.getRoomList(3,"");

        for(int i = 0;i<l.size();i++) {

            MyRoomList mrl = l.get(i);

            System.out.println(mrl.toString()); // Just to test we get the right data from db
        }
    }
}
