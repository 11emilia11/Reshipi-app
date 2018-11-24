package com.example.auricelia.reshipi

import android.content.Intent
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
                Toast.makeText(this,"Por favor digite seu nome" , Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(edittxtEmail.text.toString()))
            {
                Toast.makeText(this, "Por favor digite seu email" , Toast.LENGTH_SHORT).show()
            }
            else if ( !editTextSenhaCadastro.text.toString().equals(editTextConfSenha.text.toString())
                       || TextUtils.isEmpty(editTextSenhaCadastro.text.toString()) )
            {
                Toast.makeText(this,"Senhas não conferem", Toast.LENGTH_LONG).show()
            }
            else
            {

                Toast.makeText(this, "Cadastro Realizado com sucesso", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)


            }
        }
    }
}
