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

class Clima : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)




        if (Network.hayRed(this))
        {
            solicitudHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=3616253&appid=d3082f4bab5291b3ce8040e32600636a&lang=es&units=metric")
        }
        else {
            Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
        }
    }
    fun solicitudHTTPVolley(Url:String)
    {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET,Url,Response.Listener<String> {
                response ->
            try {
                Log.d("ResultadoVolley",response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                val tvGrado: TextView = findViewById(R.id.tvgrado)
                val tvCiuda: TextView = findViewById(R.id.tvciudad)
                val tvDescripcion: TextView = findViewById(R.id.tvciudad)
                tvGrado.text = ciudad.main!!.Temp.toString()
                tvCiuda.text = ciudad.name
                tvDescripcion.text =ciudad.weather!!.get(0).descripcion
            } catch (e:Exception){
             
            }
        }, Response.ErrorListener {
            Log.e("ErrorVolley","ErrorExtra") }
        )
        queue.add(solicitud)
    }
}