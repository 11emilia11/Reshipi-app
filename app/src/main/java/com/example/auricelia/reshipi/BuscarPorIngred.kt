package com.example.auricelia.reshipi

import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_buscar_por_ingred.*
import android.os.StrictMode
import android.util.SparseBooleanArray
import android.widget.*
import java.text.ChoiceFormat


class BuscarPorIngred : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_por_ingred)

        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, KNN.getInstancia().listarIngredientesCadastrados())
            ListView1.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            ListView1.adapter = viewAdapter



            ListView1.setOnItemClickListener { parent, view, position, id ->
                val checkedTextView = view as CheckedTextView
                checkedTextView.isChecked = !checkedTextView.isChecked
            }

            button3.setOnClickListener {

                val checked = ListView1.checkedItemPositions
                val escolhidos = ArrayList<String>()

                for (x in 0..checked.size())
                {
                    val position = checked.keyAt(x)

                    if (checked.valueAt(x))
                    {
                        escolhidos.add(viewAdapter.getItem(position))
                    }
                }

                val i = Intent(this, ReceitaShow::class.java)
                i.putStringArrayListExtra("escolhidos",escolhidos)
                startActivity(i)










            }


        }





    }
}



