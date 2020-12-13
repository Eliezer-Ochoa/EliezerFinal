package com.eliezer.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Password : AppCompatActivity() {

    private var etEmail: EditText? = null
    private var btnenviar: Button? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        initialice()
    }

    private fun initialice() {
        etEmail = findViewById<View>(R.id.editusuario) as EditText
        btnenviar = findViewById<View>(R.id.btnenviar) as Button
        mAuth = FirebaseAuth.getInstance()
        btnenviar!!.setOnClickListener { sendPasswordResetEmail() }
}
    private fun sendPasswordResetEmail() {
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email Enviado", Toast.LENGTH_SHORT).show()
                        goMain()
                    } else {
                        Toast.makeText(
                            this,
                            "No se encontró el usuario con este correo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Agregue el correo", Toast.LENGTH_SHORT).show()
        }
    }
        private fun goMain() {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }