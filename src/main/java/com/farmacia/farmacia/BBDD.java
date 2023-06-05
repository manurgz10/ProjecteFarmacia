package com.farmacia.farmacia;

import java.sql.*;

public class BBDD {
    private Connection con;
    private Statement st;

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
        }

        con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia","root","9571");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
        }


        st = null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print("Error a BBDD .connection.Class"+ e.getMessage());
        }
    }

    public ResultSet loadSelect(String query) {
        ResultSet rs;
        rs = null;
        try {
            System.out.println(query);
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error a BBDD.loadSelect.executeQuery: " +e.getMessage());
        }
        return rs;
    }

    public void updateDoctor(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error a BBDD.updateDoctor: " + e.getMessage());
        }
    }

    public void executeInsert(String query) {
        try {
            st.execute(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            System.out.print("Error en BBDD.executeInsert(): " + e.getMessage());
        }
    }
}
