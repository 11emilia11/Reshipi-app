package com.example.auricelia.reshipi

import Dados.Reporeceitas
import algoritmosIA.KNN
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_buscar_por_ingred.*
import android.os.StrictMode
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*
import java.text.ChoiceFormat


class BuscarPorIngred : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_por_ingred)

    }

    override fun onResume() {
        super.onResume()

        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            var viewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, KNN.getInstancia().listarIngredientesCadastrados())
            ListView1.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            ListView1.adapter = viewAdapter


            btnPesquisarIngr.setOnClickListener {

                var checked = ListView1.checkedItemPositions
                var escolhidos = ArrayList<String>()


                for (x in 0..(checked.size()-1))
                {
                    var position = checked.keyAt(x)

                    if (checked.valueAt(x))
                    {
                        escolhidos.add(viewAdapter.getItem(position))
                    }
                }

                var i = Intent(this, ReceitaShow::class.java)
                i.putStringArrayListExtra("escolhidos", escolhidos)
                startActivity(i)
        }


        }
    }


}





