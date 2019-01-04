package com.example.auricelia.reshipi

import Beans.Receita
import Dados.Reporeceitas
import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_receita_show.*

class ReceitaShow : AppCompatActivity() {

    var resultado : ArrayList<Receita> = ArrayList<Receita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita_show)


        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            Reporeceitas.getInstancia()


            var array = intent.getStringArrayListExtra("escolhidos")
            this.resultado = KNN.getInstancia().knn(array, 1)

            ReceitaName.setText(resultado.get(0).nome)
            var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
            ListView1ShowReceita.adapter = viewAdapter


            PorcentagemTxtView.setText(KNN.getInstancia().listarReceitasNormalizadas().size.toString())
        }


    }



}
