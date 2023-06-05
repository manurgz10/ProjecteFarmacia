package com.farmacia.farmacia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Medicina {
    private int id;
    private String name;
    private float tMax;
    private float tMin;

    public Medicina() {
    }

    public Medicina(int id, String name, float tMax, float tMin) {
        this.id = id;
        this.name = name;
        this.tMax = tMax;
        this.tMin = tMin;
    }

    public void load(int id) {
        BBDD bd = new BBDD();
        bd.conectar();

        String query = "SELECT * FROM medicine WHERE id = " + id;

        ResultSet resultSet = bd.loadSelect(query);

        try {
            // si hay resultado entramos al if
            if (resultSet.next()) {
                int idMedic = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int tempMax = resultSet.getInt("tmax");
                int tempMin = resultSet.getInt("tmin");


                this.id = idMedic;
                this.name = name;
                this.tMax = tempMax;
                this.tMin = tempMin;
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos de Medicina: " + e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTMax() {
        return tMax;
    }

    public void setTMax(float tMax) {
        this.tMax = tMax;
    }

    public float getTMin() {
        return tMin;
    }

    public void setTMin(float tMin) {
        this.tMin = tMin;
    }
}
