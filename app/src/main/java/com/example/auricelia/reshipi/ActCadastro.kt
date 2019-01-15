package com.example.auricelia.reshipi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

                var usuario = Usuario(nomeET.text.toString(), cursoSpinner.selectedItem.toString(),
                        campusSpinner.selectedItem.toString(), loginET.text.toString(), senhaET.text.toString())
                usuario.EncodeString()

                usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (verificarDados(usuario)){
                            if (usuario.senha.length < 6){
                                Toast.makeText(applicationContext, R.string.senha_pequena,
                                        Toast.LENGTH_SHORT).show()
                            }else{
                                if (dataSnapshot.child(usuario.email).exists()){
                                    Toast.makeText(applicationContext, R.string.email_cadastrado,
                                            Toast.LENGTH_SHORT).show()
                                }else{
                                    usuario.DecodeString()
                                    mAuth!!.createUserWithEmailAndPassword(usuario.email,usuario.senha)

                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    usuario.EncodeString()
                                                    usuarios!!.child(usuario.email).setValue(usuario)
                                                    Toast.makeText(applicationContext,
                                                            R.string.usuario_criado, Toast.LENGTH_SHORT).show()
                                                    val intent = Intent(applicationContext, Main2Activity::class.java)
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
                        }
                    }
                })


                Toast.makeText(this, "Cadastro Realizado com sucesso", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)


            }
        }
    }
}
