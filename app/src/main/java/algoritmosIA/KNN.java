package algoritmosIA;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import Beans.Receita;
import Beans.ReceitasNormalizadas;
import Dados.Reporeceitas;

public class KNN {

    private static KNN instancia;
    private ArrayList<String> comparador;
    private ArrayList<ReceitasNormalizadas> receitasNorm;

    private KNN() {

        this.comparador = this.construtor();
        this.receitasNorm = this.construtorReceitasNorm();

    }

    public static KNN getInstancia() {
        if (instancia == null) {
            instancia = new KNN();
        }

        return instancia;
    }

    public ArrayList<String> listarIngredientesCadastrados() {

        return this.comparador;
    }

    public ArrayList<ReceitasNormalizadas> listarReceitasNormalizadas() {

        return this.receitasNorm;
    }

    public ArrayList<Integer> normalizerReceitaRecebida(ArrayList<String> array) {
        ArrayList<Integer> retorno = new ArrayList<>();

        for (int x = 0; x < this.comparador.size(); x++) {
            retorno.add(0);
        }

        for (int x = 0; x < array.size(); x++) {

            int local = this.comparador.indexOf(array.get(x));
            if (local != -1) {
                retorno.set(local, 1);
            }

        }

        return retorno;
    }

    public void distanciaManhattan(ArrayList<Integer> ReceitaRecebida, ArrayList<Receita> distancia)
    {
        for(int x = 0 ; x < this.receitasNorm.size(); x++)
        {
            double dist = 0;
            for(int y = 0 ; y < this.comparador.size() ; y++)
            {
                dist += Math.abs(this.receitasNorm.get(x).getIngredientes().get(y) - ReceitaRecebida.get(y));
            }

            distancia.get(x).setDistancia(dist);
        }
    }

    public void distanciaEuclidiana(ArrayList<Integer> ReceitaRecebida, ArrayList<Receita> distancia) {
        double dist = 0;

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            dist = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                double local = this.receitasNorm.get(x).getIngredientes().get(y) - ReceitaRecebida.get(y);
                dist += Math.pow(local, 2);
                local = 0;
            }

            dist = Math.sqrt(dist);
            distancia.get(x).setDistancia(dist);
            dist = 0;
        }
    }

    public void knnOut(ArrayList<String> array, int k) {
        ArrayList<Receita> r = this.knn(array, k);

        System.out.println("Receitas possiveis:");
        for (int x = 0; x < k; x++) {
            System.out.println("--- " + r.get(x).getNome());
            System.out.println(r.get(x).listarIngredientes());
            System.out.println("Distancia: " + r.get(x).getDistancia() + " Porcentagem de match: " + r.get(x).getPorcentagem());
            System.out.println();
        }

    }

    public void DistanciaMatch(ArrayList<Receita> distancia, ArrayList<Integer> ReceitaRecebida) {
        int match = 0;

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            match = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                if (this.receitasNorm.get(x).getIngredientes().get(y) == 1 && ReceitaRecebida.get(y) == 1) {
                    match++;
                }
            }

            distancia.get(x).setDistancia(match);
        }

    }

    // PEARSON

    public ArrayList<Double> mediaArrayNormalizado(ArrayList<ReceitasNormalizadas> array) {
        ArrayList<Double> xmedio = new ArrayList<>();

        for (int x = 0; x < array.size(); x++) {
            double soma = 0;
            double media = 0;
            for (int y = 0; y < array.get(x).getIngredientes().size(); y++) {
                soma += array.get(x).getIngredientes().get(y);
            }

            media = (soma / array.get(x).getIngredientes().size());

            xmedio.add(media);
        }

        return xmedio;
    }

    public double mediaReceitaRecebida(ArrayList<Integer> ReceitaRecebida) {
        double soma = 0;
        double media = 0;

        for (int x = 0; x < ReceitaRecebida.size(); x++) {
            soma += ReceitaRecebida.get(x);
        }

        media = (soma / ReceitaRecebida.size());

        return media;
    }

    public ArrayList<Double> SomatorioCov(ArrayList<Double> xmedio, double ymedio, ArrayList<Integer> ReceitaRecebida) {

        ArrayList<Double> somatoriocov = new ArrayList<>();

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            double somatoriocov1 = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                somatoriocov1 += (this.receitasNorm.get(x).getIngredientes().get(y) - xmedio.get(x))
                        * (ReceitaRecebida.get(y) - ymedio);
            }

            somatoriocov.add(somatoriocov1);
        }

        return somatoriocov;
    }

    public ArrayList<Double> SomatorioVarX(ArrayList<Double> xmedio, ArrayList<Integer> ReceitaRecebida) {
        ArrayList<Double> somatoriocov = new ArrayList<>();

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            double somatoriocov1 = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                somatoriocov1 += Math.pow(((this.receitasNorm.get(x).getIngredientes().get(y) - xmedio.get(x))), 2);
            }

            somatoriocov1 = Math.sqrt(somatoriocov1);

            somatoriocov.add(somatoriocov1);
        }

        return somatoriocov;
    }

    public double SomatorioVarY(double ymedio, ArrayList<Integer> ReceitaRecebida) {
        double somatoriovarY = 0;

        for (int x = 0; x < ReceitaRecebida.size(); x++) {

            somatoriovarY += Math.pow((ReceitaRecebida.get(x) - ymedio), 2);
        }

        somatoriovarY = Math.sqrt(somatoriovarY);

        return somatoriovarY;
    }

    public void DistanciaPearson(ArrayList<Receita> distancia, ArrayList<Integer> ReceitaRecebida) {
        ArrayList<Double> xmedio = this.mediaArrayNormalizado(this.receitasNorm);
        double ymedio = this.mediaReceitaRecebida(ReceitaRecebida);

        ArrayList<Double> somatoriocov = this.SomatorioCov(xmedio, ymedio, ReceitaRecebida);
        ArrayList<Double> somatoriovarX = this.SomatorioVarX(xmedio, ReceitaRecebida);
        double somatoriovarY = this.SomatorioVarY(ymedio, ReceitaRecebida);

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            double distancia2 = 0;

            distancia2 = (somatoriocov.get(x) / (somatoriovarX.get(x) * somatoriovarY));
            distancia.get(x).setDistancia(distancia2);
        }

    }

    // END PEARSON

    public void DistanciaHamming(ArrayList<Receita> distancia, ArrayList<Integer> ReceitaRecebida) {

        int xor = 0;

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            xor = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                if (this.receitasNorm.get(x).getIngredientes().get(y) != ReceitaRecebida.get(y)) {
                    xor++;
                }
            }

            distancia.get(x).setDistancia(xor);
        }
    }

    public void MaiorDistancia(ArrayList<Receita> rec, int k, ArrayList<Receita> distancias) {
        int maior = 0;

        for (int x = 0; x < k; x++) {
            maior = 0;

            for (int y = 0; y < distancias.size(); y++) {
                if (distancias.get(maior).getDistancia() <= distancias.get(y).getDistancia()) {
                    maior = y;
                }

            }

            rec.add(distancias.get(maior));
            distancias.remove(maior);

        }
    }

    public void MenorDistancia(ArrayList<Receita> rec, int k, ArrayList<Receita> distancias) {
        int menor = 0;

        for (int x = 0; x < k; x++) {
            menor = 0;

            for (int y = 0; y < distancias.size(); y++) {
                if (distancias.get(menor).getDistancia() >= distancias.get(y).getDistancia()) {
                    menor = y;
                }

            }

            rec.add(distancias.get(menor));
            distancias.remove(menor);

        }
    }

    public void PorcentagemMatch(ArrayList<Receita> distancia, ArrayList<Integer> ReceitaRecebida)
    {
        int match = 0;
        double porcentagem = 0;

        for (int x = 0; x < this.receitasNorm.size(); x++) {
            match = 0;
            porcentagem = 0;
            for (int y = 0; y < this.comparador.size(); y++) {
                if (this.receitasNorm.get(x).getIngredientes().get(y) == 1 && ReceitaRecebida.get(y) == 1) {
                    match++;
                }
            }

            porcentagem = ((100*match)/distancia.get(x).listarIngredientes().size());

            distancia.get(x).setPorcentagem(porcentagem);
        }

    }

    public ArrayList<Receita> knn(ArrayList<String> array, int k) {
        ArrayList<Receita> rec = new ArrayList<>();
        ArrayList<Integer> receitaRecebida = this.normalizerReceitaRecebida(array);
        ArrayList<Receita> distancias = Reporeceitas.getInstancia().listarReceitas();

        this.DistanciaPearson(distancias, receitaRecebida);

        this.PorcentagemMatch(distancias, receitaRecebida);

        this.MaiorDistancia(rec, k, distancias);



        return rec;
    }

    public ArrayList<ReceitasNormalizadas> construtorReceitasNorm() {
        ArrayList<ReceitasNormalizadas> rec = this.geradorReceitarNorm();

        for (int x = 0; x < Reporeceitas.getInstancia().listarReceitas().size(); x++) {
            for (int y = 0; y < Reporeceitas.getInstancia().listarReceitas().get(x).listarIngredientes().size(); y++) {

                int local = this.comparador
                        .indexOf(Reporeceitas.getInstancia().listarReceitas().get(x).listarIngredientes().get(y));
                rec.get(x).getIngredientes().set(local, 1);

            }
        }

        return rec;
    }

    public ArrayList<ReceitasNormalizadas> geradorReceitarNorm() {
        ArrayList<ReceitasNormalizadas> rec = new ArrayList<>();

        // TRANSFORMANDO O ARRAY DE INGREDIENTES EM UM ARRAY DE 0 COM O TAMANHO DO ARRAY
        // DE INGREDIENTES TOTAL E ADICIONANDO O NOME
        for (int x = 0; x < Reporeceitas.getInstancia().listarReceitas().size(); x++) {
            ReceitasNormalizadas r = new ReceitasNormalizadas(
                    Reporeceitas.getInstancia().listarReceitas().get(x).getNome());

            rec.add(r);

            for (int y = 0; y < this.comparador.size(); y++) {
                rec.get(x).addIngre(0);
            }

        }

        return rec;
    }

    public ArrayList<String> construtor() {
        ArrayList<String> retorno = new ArrayList<>();

        for (int x = 0; x < Reporeceitas.getInstancia().listarReceitas().size(); x++) {
            for (int y = 0; y < Reporeceitas.getInstancia().listarReceitas().get(x).listarIngredientes().size(); y++) {
                if (!retorno
                        .contains(Reporeceitas.getInstancia().listarReceitas().get(x).listarIngredientes().get(y))) {
                    retorno.add(Reporeceitas.getInstancia().listarReceitas().get(x).listarIngredientes().get(y));
                }
            }
        }

        Collections.sort(retorno);
        return retorno;
    }

}

