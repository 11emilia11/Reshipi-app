package com.example.auricelia.reshipi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class ActMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBuscar.setOnClickListener {

            val i = Intent(this, BuscarPorIngred::class.java)
            startActivity(i)
        }


        btnListar.setOnClickListener {

            val i = Intent(this, receitalista::class.java)
            startActivity(i)
        }


    }
}
