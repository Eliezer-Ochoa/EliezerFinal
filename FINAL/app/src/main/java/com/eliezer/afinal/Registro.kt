package com.eliezer.afinal

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Registro : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


/*
       val equipos  : Cursor = db.rawQuery("SELECT  * from Equipos, LIKE ", null)

        if (equipos != null) {
            equipos.moveToFirst()
            do {
                //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos

                val codigo: String = equipos.getString(equipos.getColumnIndex("codigo"))
                val nombre: String = equipos.getString(equipos.getColumnIndex("nombre"))
                val marca: String = equipos.getString(equipos.getColumnIndex("marca"))
                val modelo: String = equipos.getString(equipos.getColumnIndex("modelo"))
                val serie: String = equipos.getString(equipos.getColumnIndex("serie"))
                val precio: String = equipos.getString(equipos.getColumnIndex("precio"))
                val estado: String = equipos.getString(equipos.getColumnIndex("estado"))
                val fecha: String = equipos.getString(equipos.getColumnIndex("fecha"))
                val cantidad: String = equipos.getString(equipos.getColumnIndex("cantidad"))

            } while (equipos.moveToNext())
        }
        equipos.close()

        //Cerramos el cursor y la conexion con la base de datos
        //Cerramos el cursor y la conexion con la base de datos

 */

    }


    fun lanzarClima(view: View) {
        val intent = Intent(this, Clima::class.java)
        startActivity(intent)
    }
}
