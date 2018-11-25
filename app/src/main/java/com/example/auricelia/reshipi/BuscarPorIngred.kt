package com.example.auricelia.reshipi

import algoritmosIA.KNN
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_buscar_por_ingred.*
import kotlin.collections.ArrayList

class BuscarPorIngred : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_por_ingred)


        val arr = arrayListOf<String>()
        arr.add("Leonardo")
        arr.add("Marcos")
        arr.add("Fernando")

        val file = assets.open("reshipejavafile3.txt")
       val array = KNN.getInstancia(file).listarIngredientesCadastrados()
       val mAdapter = CustomAdapter(this,arr)
        RecView1.adapter = mAdapter


    }
}
