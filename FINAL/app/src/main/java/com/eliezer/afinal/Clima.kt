package com.eliezer.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class Clima : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        if (Network.hayRed(this))
        {
            Toast.makeText(this,"hay red",Toast.LENGTH_LONG).show()
            solicitudHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=3616253&appid=d3082f4bab5291b3ce8040e32600636a&lang=es&units=metric")
        } else {
            Toast.makeText(this,"No hay red",Toast.LENGTH_LONG).show()
        }
    }

    fun solicitudHTTPVolley(url:String)
    {

        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET,url, {
                response ->
            try {
                Log.d("ResultadoVolley",response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                val tvGrado: TextView = findViewById(R.id.tvgrado)
                val tvCiuda: TextView = findViewById(R.id.tvciudad)
                val tvDescripcion: TextView = findViewById(R.id.tvdescripcion)
                tvGrado.text = ciudad.main!!.Temp.toString() + "°"
                tvCiuda.text = ciudad.name
                tvDescripcion.text = ciudad.weather!!.get(0).descripcion
            } catch (e:Exception){

            }
        }, {
            Log.e("ErrorVolley","ERROR EN LA CONSULTA")
        })
        queue.add(solicitud)
}}
