package Exceptions;

public class Objectnotfound extends Exception {

    private String name;

    public Objectnotfound(String name)
    {
        super("Not found");
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}

