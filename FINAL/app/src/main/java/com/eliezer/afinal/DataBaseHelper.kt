package com.eliezer.afinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper(context: Context): SQLiteOpenHelper(context, EquiposContract.Companion.Entrada.NOMBRE_TABLA, null, EquiposContract.Companion.VERSION){
   //Creación de tabla
    companion object{
        val CREATE_EQUIPOS_TABLA = "CREATE TABLE" + EquiposContract.Companion.Entrada.NOMBRE_TABLA +
                "(" + EquiposContract.Companion.Entrada.COLUMNA_ID + "TEXT PRIMARY KEY, " +
                EquiposContract.Companion.Entrada.COLUMNA_NOMBRE +   EquiposContract.Companion.Entrada.COLUMNA_MARCA +
                EquiposContract.Companion.Entrada.COLUMNA_MARCA +   EquiposContract.Companion.Entrada.COLUMNA_MODELO +
                EquiposContract.Companion.Entrada.COLUMNA_SERIE +   EquiposContract.Companion.Entrada.COLUMNA_PRECIO +
                EquiposContract.Companion.Entrada.COLUMNA_ESTADO +   EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA +
                EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD + " TEXT )"

        val REMOVE_EQUIPOS_TABLA = "DROP TABLE IF EXISTTS" + EquiposContract.Companion.Entrada.NOMBRE_TABLA
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_EQUIPOS_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Configuración del metodo onUPgrade
        //Elimianción de table
        db?.execSQL(REMOVE_EQUIPOS_TABLA)
        //Volver a crearla
        onCreate(db)
    }

}