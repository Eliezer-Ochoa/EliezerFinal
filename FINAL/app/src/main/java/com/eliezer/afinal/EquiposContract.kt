package com.eliezer.afinal

import android.os.Build
import android.provider.BaseColumns

class EquiposContract {
   //Creación de las variables estáticas
    companion object{
        val VERSION = 1
        class Entrada: BaseColumns{
            companion object{
                val NOMBRE_TABLA = "equipos"

                val COLUMNA_ID = "codigo"
                val COLUMNA_NOMBRE = "nombre"
                val COLUMNA_MARCA = "marca"
                val COLUMNA_MODELO = "modelo"
                val COLUMNA_SERIE = "serie"
                val COLUMNA_PRECIO = "precio"
                val COLUMNA_ESTADO = "estado"
                val COLUMNA_FECHACOMPRA = "fechacompra"
                val COLUMNA_CANTIDAD = "cantidad"
            }
        }
    }
}