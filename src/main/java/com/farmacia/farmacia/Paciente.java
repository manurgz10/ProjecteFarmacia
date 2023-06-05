package com.farmacia.farmacia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Paciente extends Persona{


    public Paciente(String mail, String name) {
        super(mail,name);
    }

    public Paciente() {}

    @Override
    public void load(String id) {
        BBDD bd = new BBDD();
        bd.conectar();

        // Query para otener todos los xips con nuestro id
        String query = "SELECT * FROM patient WHERE mail = '" + id + "'";

        ResultSet resultSet = bd.loadSelect(query);

        try {
            // si hay resultado entramos al if
            if (resultSet.next()) {
                String patientName = resultSet.getString("name");
                String patientMail = resultSet.getString("mail");


                this.name = patientName;
                this.mail = patientMail;
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos del Xip: " + e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
    }
    }
