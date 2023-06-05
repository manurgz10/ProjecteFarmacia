package com.farmacia.farmacia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.lang.StringBuilder;

public class Doctor extends Persona {

    private String pass;
    private LocalDate lastlog;
    private int session;
    private ArrayList releaseList;


    public Doctor() {
        this.releaseList=new ArrayList<>();
    }

    public Doctor(String name, String mail, String pass, LocalDate lastlog, int session) {
        super(name,mail);
        this.pass=pass;
        this.lastlog=lastlog;
        this.session=session;
        this.releaseList=new ArrayList<>();
    }

    public void load(String id) {
        String query = "SELECT * FROM doctor WHERE mail='" + id +"';";
        BBDD bd = new BBDD();
        bd.conectar();
        ResultSet rs = null;

        try {
            rs = bd.loadSelect(query);
            if (rs.next() && rs !=null) {
                this.setName(rs.getString("name"));
                this.setMail(rs.getString("mail"));
                this.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            System.out.println("Error a Doctor.load: " + e.getMessage());
        }
    }

    public void login(String mail, String pass) throws SQLException {
        String query = "SELECT * FROM doctor WHERE mail='" + mail + "' AND pass= '" + pass + "';" ;
        BBDD bd = new BBDD();
        bd.conectar();
        ResultSet rs = bd.loadSelect(query);
        if (rs.next()) {
            this.setLastlog(LocalDate.now());
            Random random = new Random();
            String code = "1";
            for (int i = 0; i<9; i++ ) {
                code+=random.nextInt(10);
            }
            int session = Integer.parseInt(code);
            this.setSession(session);

            query="UPDATE doctor SET last_Log= '" + this.getLastlog() + "',session= '" + this.getSession() + "' WHERE mail= '" + mail + "';";
            bd.updateDoctor(query);

            this.load(mail);
        }
    }

    public boolean isLogged(String email, String session) {
        String query = "SELECT * FROM doctor WHERE mail = '" + email + "' AND session = '" + session + "';";
        BBDD bd = new BBDD();

        try {
            bd.conectar();
            ResultSet rs = bd.loadSelect(query);
            if (rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            System.out.print("Error a BBDD .exeIsLogged.Class"+ e.getMessage());
        }

        return false;
    }

    public ArrayList<Xip> loadReleaseList() {
        String query = "SELECT * FROM xip WHERE doctor_mail='" + mail +"'";
        ArrayList<Xip> releaseList = new ArrayList<>();
        BBDD bd = new BBDD();
        bd.conectar();
        ResultSet resultSet = bd.loadSelect(query);

        try {
            while (resultSet.next()) {
                System.out.println("e");
                int id = resultSet.getInt("id");
                Xip xip = new Xip();
                xip.load(id);

                int idMedicina = resultSet.getInt("id_medicine");
                Medicina medicina = new Medicina();
                medicina.load(idMedicina);
                xip.setMedicina(medicina);

                String patientName = resultSet.getString("id_patient");
                Paciente paciente = new Paciente();
                paciente.load(patientName);
                xip.setPaciente(paciente);
                System.out.println(xip.toString());
                releaseList.add(xip);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return releaseList;
    }

    public String getTable() {
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table>");
        htmlTable.append("<tr><th>ID</th><th>Paciente</th><th>Medicina</th><th>Fecha</th></tr>");
        ArrayList<Xip> xips = this.loadReleaseList();

        for (int i = 0; i < xips.size(); i++) {
            Xip xip = xips.get(i);
            int id = xip.getId();
            String paciente = xip.getPaciente().getMail();
            String medicina = xip.getMedicina().getName();
            String fecha = xip.getEndDate().toString();

            htmlTable.append("<tr>");
            htmlTable.append("<td>").append(id).append("</td>");
            htmlTable.append("<td>").append(paciente).append("</td>");
            htmlTable.append("<td>").append(medicina).append("</td>");
            htmlTable.append("<td>").append(fecha).append("</td>");
            htmlTable.append("</tr>");
        }
        htmlTable.append("</table>");
        return htmlTable.toString();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDate getLastlog() {
        return lastlog;
    }

    public void setLastlog(LocalDate lastlog) {
        this.lastlog = lastlog;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public ArrayList getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(ArrayList releaseList) {
        this.releaseList = releaseList;
    }
}