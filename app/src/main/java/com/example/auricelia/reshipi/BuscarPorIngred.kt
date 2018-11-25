package com.example.auricelia.reshipi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_buscar_por_ingred.*

class BuscarPorIngred : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_por_ingred)

        val arrayAramazena = arrayListOf<String>()

        val myDataset = arrayOf("Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo"
        ,"Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo"
        ,"Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo"
        ,"Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo"
        ,"Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo","Leonardo")


        val viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myDataset)
        ListView1.adapter = viewAdapter
        

    }
}



