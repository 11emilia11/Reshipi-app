package com.example.auricelia.reshipi

import Beans.Receita
import Beans.Usuario
import Dados.Reporeceitas
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.inicialscreen.*
import kotlinx.android.synthetic.main.loginscreen.*

class MainActivity : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var receitas : DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)



        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")
        receitas = database!!.getReference("Receitas")
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }


        receitas!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
            var array = ArrayList<Receita>()
            for(x in 0..(dataSnapshot.childrenCount-1))
            {
                var Receita = dataSnapshot.child(x.toString()).getValue(Receita::class.java)
                Receita!!.setIngredientes(dataSnapshot.child(x.toString()).child("Ingredientes").value as java.util.ArrayList<String>)
                array.add(Receita as Receita)
            }
                Reporeceitas.getInstancia().updateReceitas(array)
            }
        })

        //var cont = 0 //FUNÇÃO QUE FEZ O CADASTRO DAS RECEITA
        //for(x in Reporeceitas.getInstancia().listarReceitas()){

          //  receitas!!.child(cont.toString()).setValue(x)
           // receitas!!.child(cont.toString()).child("Ingredientes").setValue(x.listarIngredientes())
            //cont += 1
        //}

        buttoncadastro.setOnClickListener {

            val i = Intent(this, ActCadastro::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener {
            var usuario = Usuario(editTextEmail.text.toString(), editTextSenha.text.toString())
            usuario.EncodeString()
            this.entrar(usuario)
        }

    }

    fun loadFirebase(): ArrayList<Receita>{

        var Receitas = ArrayList<Receita>()



        return Receitas
    }

    fun entrar(usuario: Usuario){

        usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (verificarDados(usuario)){

                    if (dataSnapshot.child(usuario.email).exists()) {

                        var login = dataSnapshot.child(usuario.email).getValue(Usuario::class.java)

                        if(login!!.senha.equals(usuario.senha)){

                            usuario.DecodeString()
                            mAuth!!.signInWithEmailAndPassword(usuario.email,usuario.senha)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(applicationContext, R.string.ToasLoginOK, Toast.LENGTH_SHORT).show()
                                            val intent = Intent(applicationContext, ActMenu::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                                    }

                        } else{
                            Toast.makeText(applicationContext, R.string.ToastPassWrong, Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext, R.string.ToastEmailWR, Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(applicationContext, R.string.ToastDigiteWR, Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

    private fun verificarDados(usuario: Usuario): Boolean {
        if(usuario.email.isBlank() || usuario.senha.isBlank()){
            return false
        }

        return true
    }


}
