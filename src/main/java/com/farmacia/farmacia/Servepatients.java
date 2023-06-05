package com.farmacia.farmacia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.*;

import javax.print.Doc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "Servepatients", value = "/Servepatients")
public class Servepatients extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        ArrayList<String> listaPacientes = new ArrayList<>();
        BBDD bd = new BBDD();
        Doctor d = new Doctor();
        bd.conectar();

        try {
            String query = "SELECT mail, name FROM patient";
            ResultSet resultSet = bd.loadSelect(query);
            while(resultSet.next()) {
                String mail1 = resultSet.getString("mail");
                String name = resultSet.getString("name");
                listaPacientes.add(name);
                listaPacientes.add(mail1);
            }
            resultSet.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        JSONArray jsonArray = new JSONArray(listaPacientes);
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}