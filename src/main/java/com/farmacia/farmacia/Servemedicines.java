package com.farmacia.farmacia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "Servemedicines", value = "/Servemedicines")
public class Servemedicines extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        ArrayList<String> listaMedicinas = new ArrayList<>();
        BBDD bd = new BBDD();
        Doctor d = new Doctor();
        bd.conectar();

        try {
            String query = "SELECT name, id FROM medicine";
            ResultSet resultSet = bd.loadSelect(query);
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                listaMedicinas.add(name);
                listaMedicinas.add(id);

            }
            resultSet.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        JSONArray jsonArray = new JSONArray(listaMedicinas);
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}