package com.example.auricelia.reshipi

import Dados.Reporeceitas
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.inicialscreen.*
import kotlinx.android.synthetic.main.loginscreen.*

class MainActivity : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

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

        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        btnInicial.setOnClickListener {

            val i = Intent(this, ActMenu::class.java)
            startActivity(i)
        }

    }


}
