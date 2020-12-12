package com.eliezer.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)
    }

    fun lanzarclima(view: View) {
        val intent = Intent(this, Clima::class.java)
        startActivity(intent)
    }
}