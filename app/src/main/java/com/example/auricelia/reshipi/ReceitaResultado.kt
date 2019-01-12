package com.example.auricelia.reshipi

import Dados.Reporeceitas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_receita_resultado.*

class ReceitaResultado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita_resultado)

        var receitaescolhida = intent.getStringExtra("escolhida")
        var receita = Reporeceitas.getInstancia().buscarReceita(receitaescolhida)

        RecName.setText(receita.nome)
        var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, receita.listarIngredientes())
        ListViewRecSHOWN.adapter = viewAdapter



    }
}
