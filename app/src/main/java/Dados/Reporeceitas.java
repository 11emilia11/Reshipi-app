package Dados;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Beans.Receita;
import Exceptions.Objectnotfound;
import Exceptions.Objetojaexiste;
import Beans.Receita;
import Dados.Reporeceitas;
import algoritmosIA.KNN;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Reporeceitas {

    private static Reporeceitas instancia;
    private ArrayList<Receita> receitas;

    private Reporeceitas()
    {
        this.receitas = new ArrayList<>();
    }

    public static Reporeceitas getInstancia(InputStream file)
    {
        if(instancia == null)
        {
            instancia = Reporeceitas.load(file);
        }

        return instancia;
    }

    public void cadastrarReceita(Receita r) throws  Objetojaexiste
    {
        boolean result = this.existe(r.getNome());

        if(result == true)
        {
            throw new Objetojaexiste(r.getNome());
        }
        else
        {
            this.receitas.add(r);
        }




    }

    public void removerReceita(String nome) throws Objectnotfound
    {
        Receita r = this.buscarReceita(nome);
        this.receitas.remove(r);

    }

    public Receita buscarReceita(String nome) throws Objectnotfound
    {
        Receita resultado = null;

        int i = this.procurarIndice(nome);
        resultado = this.receitas.get(i);
        return resultado;

    }

    public int procurarIndice(String nome) throws Objectnotfound
    {
        int cont = -1;

        for(int x = 0 ; x < this.receitas.size() ; x++)
        {
            if(this.receitas.get(x).getNome().equals(nome))
            {
                cont = x;
            }
        }

        if(cont < 0)
        {
            throw new Objectnotfound(nome);
        }

        return cont;
    }

    public boolean existe(String nome)
    {
        boolean resultado = false;

        for(int x = 0 ; x < this.receitas.size() ; x++)
        {
            if(this.receitas.get(x).getNome().equals(nome))
            {
                resultado = true;
            }
        }

        return resultado;


    }

    public ArrayList<Receita> listarReceitas()
    {
        return this.receitas;
    }



    @SuppressWarnings("resource")
    private static Reporeceitas load(InputStream file){

        Reporeceitas rep = new Reporeceitas();
        int contNome = 0;
        Receita r = null;

        try {
            Scanner sc = new Scanner(file)
                    .useDelimiter(",");


            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                Scanner lnsc = new Scanner(line).useDelimiter(",");

                while(lnsc.hasNext())
                {
                    if(contNome == 0)
                    {
                        r = new Receita(lnsc.next());
                        contNome++;
                    }
                    else
                    {
                        r.addIngre(lnsc.next().toLowerCase());
                    }

                }

                lnsc.close();
                contNome = 0;
                try {
                    rep.cadastrarReceita(r);
                } catch (Objetojaexiste e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }


            sc.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rep;
    }

}

