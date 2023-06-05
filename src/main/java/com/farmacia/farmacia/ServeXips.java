package com.farmacia.farmacia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Servexips", value = "/Servexips")
public class ServeXips extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        Doctor d = new Doctor();
        System.out.println(mail);
        System.out.println(session);
        System.out.println("1");

        try {
            if (d.isLogged(mail,session) == true) {
                System.out.println("2");
                d.load(mail);
                System.out.println("4");
                response.getWriter().write(d.getTable());
                System.out.println(d.getTable());
                System.out.println("3");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}