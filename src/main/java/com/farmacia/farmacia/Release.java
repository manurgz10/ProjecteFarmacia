package com.farmacia.farmacia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "Release", value = "/Release")
public class Release extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        String idXip = request.getParameter("xipId");
        String nombreMed = request.getParameter("medicineList");
        String date = request.getParameter("endDate");
        String nombrePaciente = request.getParameter("patientList");

        BBDD bd = new BBDD();
        bd.conectar();

        try {
            String querypaciente = "SELECT mail from patient where name = '" + nombrePaciente + "'";
            ResultSet resultSetPaciente = bd.loadSelect(querypaciente);
            resultSetPaciente.next();
            String mailP = resultSetPaciente.getString("mail");

            String querymedicine = "SELECT id from medicine where name = '" + nombreMed + "'";
            ResultSet resultSetMedicine = bd.loadSelect(querymedicine);
            resultSetMedicine.next();
            String idMed = resultSetMedicine.getString("id");

            String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (" + idXip + ", '" + mail + "', " + idMed + ", '" + mailP + "', '" + date + "')";
            bd.executeInsert(query);
            System.out.println(query);
            response.getWriter().print("Insertado correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}