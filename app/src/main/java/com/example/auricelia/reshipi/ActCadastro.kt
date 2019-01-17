package com.example.auricelia.reshipi

import Beans.Usuario
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.act_cadastro.*

class ActCadastro : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_cadastro)

        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        buttonCadastrarX.setOnClickListener {

            if (TextUtils.isEmpty(editTXTNome.text.toString()))
            {
                Toast.makeText(this,R.string.ToastNome , Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(edittxtEmail.text.toString()))
            {
                Toast.makeText(this, R.string.ToastEmail , Toast.LENGTH_SHORT).show()
            }
            else if ( !editTextSenhaCadastro.text.toString().equals(editTextConfSenha.text.toString())
                       || TextUtils.isEmpty(editTextSenhaCadastro.text.toString()) )
            {
                Toast.makeText(this,R.string.ToastSenha, Toast.LENGTH_LONG).show()
            }
            else
            {

                var usuario = Usuario(editTXTNome.text.toString(),edittxtEmail.text.toString(),editTextSenhaCadastro.text.toString())
                usuario.EncodeString()

                usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                         //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                                if (dataSnapshot.child(usuario.email).exists()){
                                    Toast.makeText(applicationContext, R.string.ToastUsuarioEx,
                                            Toast.LENGTH_SHORT).show()
                                }else{
                                    usuario.DecodeString()
                                    mAuth!!.createUserWithEmailAndPassword(usuario.email,usuario.senha)

                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    usuario.EncodeString()
                                                    usuarios!!.child(usuario.email).setValue(usuario)
                                                    Toast.makeText(applicationContext,
                                                            R.string.ToastUserCre, Toast.LENGTH_SHORT).show()

                                                    val intent = Intent(applicationContext, MainActivity::class.java)
                                                    startActivity(intent)
                                                    finish()
                                                }
                                            }

                                            .addOnFailureListener { exception ->
                                                if (exception != null) {
                                                    Toast.makeText(applicationContext,
                                                            exception.localizedMessage, Toast.LENGTH_LONG).show()
                                                }
                                            }
                                }
                            }

                })


            }
        }
    }

    private fun verificarDados(usuario: Usuario): Boolean {
        if(usuario.nome.isBlank() ||  usuario.email.isBlank() || usuario.senha.isBlank()){
            return false
        }

        return true
    }
}
