package com.example.auricelia.reshipi

import Beans.Receita
import Dados.Reporeceitas
import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.AdapterView
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
            this.resultado = KNN.getInstancia().knn(array, 5)

            var array2 = ArrayList<String>()
            array2.add(resultado.get(0).nome)
            array2.add(resultado.get(1).nome)
            array2.add(resultado.get(2).nome)
            array2.add(resultado.get(3).nome)
            array2.add(resultado.get(4).nome)


            ReceitaName.setText("Receitas possíveis a partir dos ingredientes escolhidos")
            var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array2)
            ListView1ShowReceita.adapter = viewAdapter


            ListView1ShowReceita.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->

                var i = Intent(this, ReceitaResultado::class.java)
                i.putExtra("escolhida", array2[position])
                startActivity(i)

            }
        }


    }



}
