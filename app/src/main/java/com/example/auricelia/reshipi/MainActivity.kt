package com.example.auricelia.reshipi

import Dados.Reporeceitas
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import kotlinx.android.synthetic.main.inicialscreen.*
import kotlinx.android.synthetic.main.loginscreen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicialscreen)

        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8)
        {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            Reporeceitas.getInstancia()

        }

        btnInicial.setOnClickListener {

            val i = Intent(this, ActMenu::class.java)
            startActivity(i)
        }


    }
}
