package Exceptions;

public class Objetojaexiste extends Exception{

    private String name;

    public Objetojaexiste(String name)
    {
        super("Objeto ja existe");
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

