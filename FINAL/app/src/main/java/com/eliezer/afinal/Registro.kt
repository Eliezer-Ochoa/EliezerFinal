package com.eliezer.afinal

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

    }

    fun lanzarClima(view: View) {
        val intent = Intent(this, Clima::class.java)
        startActivity(intent)
    }
}
