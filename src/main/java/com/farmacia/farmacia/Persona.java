package com.farmacia.farmacia;

public abstract class Persona {

    //A
    protected String name;
    protected String mail;

    //Constructors
    public Persona() {

    }

    public Persona(String name, String mail) {
        this.name=name;
        this.mail=mail;
    }


    public abstract void load(String id);

    //G Y S
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}