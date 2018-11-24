package com.example.auricelia.reshipi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.loginscreen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)

        buttonlogin.setOnClickListener {

            val i = Intent(this, ActAvaliarReceita::class.java)
            startActivity(i)

        }
    }
}
