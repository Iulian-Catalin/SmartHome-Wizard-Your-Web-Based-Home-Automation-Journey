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
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
            final String USERNAME = "postgres";

            final String PASSWORD = "postgres";


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO myitemlist (itemname,itemdate, iduser) VALUES(?,?, ?)");
            pSt.setString(1,u.getItemName());

            Date date = Date.valueOf(u.getItemDate());
            pSt.setDate(2, date);

            pSt.setInt(3, u.getIduser());


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

    public List<MyItemList> getItemList(int idUser, String search) {

        MyItemList mfl =null;
        List<MyItemList> list = new ArrayList<>();
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://192.168.50.128:5432/postgres";
        final String USERNAME = "postgres";

        final String PASSWORD = "postgres";
        int id =-1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul



            PreparedStatement pSt = conn.prepareStatement("select * from myitemlist where iduser=? and itemname like CONCAT( '%',?,'%') ORDER BY itemdate desc");


            pSt.setInt(1, idUser);
            pSt.setString(2, search);


            // 3. executie
            ResultSet rs = pSt.executeQuery();




            // atata timp cat am randuri
            while (rs.next()) {

                mfl = new MyItemList();
                mfl.setId(rs.getInt("id"));
                mfl.setItemName(rs.getString("itemname"));

                Date dateFromDB = rs.getDate("itemdate");
                LocalDate localDate = dateFromDB.toLocalDate();
                mfl.setItemDate(localDate);


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

//       MyItemList m0 = new MyItemList("pizza", LocalDate.now(), 48 );
//
//        MyItemList m1 = new MyItemList("cartofi cu ceapa ", LocalDate.now(), 48 );
//        MyItemList m2 = new MyItemList("peste cu morcovi", LocalDate.now(), 48 );
//        MyItemList m3 = new MyItemList("inghetata de vanilie", LocalDate.now(), 48 );
//        MyItemList m4 = new MyItemList("fructe de mare", LocalDate.now(), 48 );
//
//
//        db.newFood(m0);
//        db.newFood(m1);
//        db.newFood(m2);
//        db.newFood(m3);
//        db.newFood(m4);

        List<MyItemList> l = db.getItemList(48,"");

        for(int i = 0;i<l.size();i++) {

            MyItemList mfl = (MyItemList) l.get(i);

            System.out.println(mfl.toString()); // just to test we get the right data from db
        }




    }
}
