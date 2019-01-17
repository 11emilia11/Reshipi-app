package com.example.auricelia.reshipi

import Dados.Reporeceitas
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import java.lang.Exception

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread(){
            override fun run() {
                try {

                    val SDK_INT = android.os.Build.VERSION.SDK_INT
                    if (SDK_INT > 8)
                    {
                        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                        StrictMode.setThreadPolicy(policy)
                        Reporeceitas.getInstancia()

                    }
                    Thread.sleep(1000)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)

                }catch (e: Exception)
                {
                    e.printStackTrace()
                }
            }
        }

        background.start()
    }
}
