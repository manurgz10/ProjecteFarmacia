package com.farmacia.farmacia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Xip {
    private int id;
    private Medicina medicina;
    private Paciente paciente;
    private Date endDate;

    public Xip() {
    }

    public Xip(int id, Medicina medicine, Paciente patient, Date endDate) {
        this.id = id;
        this.medicina = medicine;
        this.paciente = patient;
        this.endDate = endDate;
    }

    public void load(int id) {
        BBDD bd = new BBDD();
        bd.conectar();

        // Query para otener todos los xips con nuestro id
        String query = "SELECT * FROM xip WHERE id = " + id;

        ResultSet resultSet = bd.loadSelect(query);

        try {
            // si hay resultado entramos al if
            if (resultSet.next()) {
                int xipId = resultSet.getInt("id");
                int medicineId = resultSet.getInt("id_medicine");
                String patientMail = resultSet.getString("id_patient");
                Date endDate = resultSet.getDate("date");


                this.id = xipId;
                this.medicina = medicina;
                this.paciente = paciente;
                this.endDate = endDate;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Xip{" +
                "id=" + id +
                ", medicina=" + medicina +
                ", paciente=" + paciente +
                ", endDate=" + endDate +
                '}';
    }
}