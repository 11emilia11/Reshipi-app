package Beans;

import java.util.ArrayList;

public class Receita {

    private String nome;
    private ArrayList<String> ingredientes;
    private double distancia;
    private double porcentagem;

    public Receita(String nome) {
        this.nome = nome;
        this.ingredientes = new ArrayList<>();
        this.distancia = 0;
        this.porcentagem = 0;
    }

    public Receita() {
        this.ingredientes = new ArrayList<>();
    }

    public double getPorcentagem()
    {
        return this.porcentagem;
    }

    public void setIngredientes(ArrayList<String> ing)
    {
        this.ingredientes = ing;
    }

    public void setPorcentagem(double porcentagem)
    {
        this.porcentagem = porcentagem;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addIngre(String ing) {
        this.ingredientes.add(ing);

    }

    public void removeIngre(String ing) {
        this.removeIngre(ing);

    }

    public ArrayList<String> listarIngredientes() {
        return this.ingredientes;
    }

    @Override
    public String toString() {
        return "Receita [nome=" + nome + ", ingredientes=" + ingredientes + "]\n\n";
    }

}

