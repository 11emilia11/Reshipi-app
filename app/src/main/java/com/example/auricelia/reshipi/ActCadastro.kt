package com.example.auricelia.reshipi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.act_cadastro.*

class ActCadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_cadastro)

        buttonCadastrarX.setOnClickListener {

            if (TextUtils.isEmpty(editTXTNome.text.toString()))
            {
                Toast.makeText(this,"Pliz enter your name" , Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(edittxtEmail.text.toString()))
            {
                Toast.makeText(this, "Pliz enter your email" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}
