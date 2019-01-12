package com.example.auricelia.reshipi

import Dados.Reporeceitas
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_receitalista.*

class receitalista : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receitalista)

        val array = ArrayList<String>()

        for (x in Reporeceitas.getInstancia().listarReceitas())
            array.add(x.nome)

        var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        LISTARECEITAMAN.adapter = viewAdapter

        LISTARECEITAMAN.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->

            var i = Intent(this, ReceitaResultado::class.java)
            i.putExtra("escolhida", Reporeceitas.getInstancia().listarReceitas()[position].nome)
            startActivity(i)

        }

    }
}
