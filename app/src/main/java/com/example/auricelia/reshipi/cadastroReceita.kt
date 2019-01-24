package com.example.auricelia.reshipi

import Beans.Receita
import Dados.Reporeceitas
import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cadastro_receita.*

class cadastroReceita : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var receitas : DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_receita)

        database = FirebaseDatabase.getInstance()
        receitas = database!!.getReference("Receitas")


        var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, KNN.getInstancia().listarIngredientesCadastrados())
        ListCadastroReceita.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        ListCadastroReceita.adapter = viewAdapter

        btnCadastrarReceita.setOnClickListener {

            var checked = ListCadastroReceita.checkedItemPositions
            var escolhidos = ArrayList<String>()
            val nomeReceita = editTextNomeReceitaCadastro.text.toString()

            if(!TextUtils.isEmpty(nomeReceita))
            {
                if(checked.size() > 0)
                {
                    for (x in 0..(checked.size()-1))
                    {
                        var position = checked.keyAt(x)

                        if (checked.valueAt(x))
                        {
                            escolhidos.add(viewAdapter.getItem(position))
                        }
                    }

                    val receita = Receita(nomeReceita)

                    receitas!!.child(Reporeceitas.getInstancia().listarReceitas().size.toString()).setValue(receita)
                    receitas!!.child(Reporeceitas.getInstancia().listarReceitas().size.toString()).child("Ingredientes").setValue(escolhidos)

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

                    KNN.getInstancia().construtor()
                    KNN.getInstancia().construtorReceitasNorm()

                    Toast.makeText(applicationContext, "Receita " + nomeReceita + " adicionada com sucesso", Toast.LENGTH_SHORT).show()

                    var i = Intent(this, ActMenu::class.java)
                    startActivity(i)

                }
                else{
                    Toast.makeText(applicationContext, "Escolha pelo menos um ingrediente", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(applicationContext, "Dê um nome a receita", Toast.LENGTH_SHORT).show()
            }


        }

    }
}
