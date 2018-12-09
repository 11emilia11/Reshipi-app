package com.example.auricelia.reshipi

import Beans.Receita
import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_receita_show.*

class ReceitaShow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita_show)

        val array = intent.getStringArrayListExtra("escolhidos")
        val resultado = KNN.getInstancia().knn(array,1)

        ReceitaName.setText(resultado.get(0).nome)
        val viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,resultado.get(0).listarIngredientes())
        ListView1ShowReceita.adapter = viewAdapter
        PorcentagemTxtView.setText("Você possui " + resultado.get(0).porcentagem + " porcento da receita original")


    }
}
