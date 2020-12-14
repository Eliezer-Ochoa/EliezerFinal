package com.eliezer.afinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class EquiposCRUD(context: Context) {

    private var helper:DataBaseHelper? = null
    init {
         helper = DataBaseHelper(context)
    }

    //crear nuevo equipo
    fun newEquipos(item:Equipos){
        //creación de una neuva BD
        //Definiendo el obejeto de BD que se referncia a helper, ejcuta exectSQL DatBase Helper
        //Abrir BD modo escritura
        val db: SQLiteDatabase = helper?.writableDatabase!!  //writableDatabasehabilitar BD para poder escribir en ella

        //Llenando los datos que queremos insertar en nuestra tabla
        // Clase que me permite empexzar agrupar información para poder mapiarla con mi tabla
        val values = ContentValues()

        //Mapeo de las columnas con valores a insertar
        values.put(EquiposContract.Companion.Entrada.COLUMNA_ID, item.codigo)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_MARCA, item.marca)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_MODELO, item.modelo)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_SERIE, item.serie)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_PRECIO, item.precio)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_ESTADO, item.estado)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA, item.fechacompra)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD, item.cantidad)

        //Insertar una nueva filka en la tabla
        val newRowId = db.insert(EquiposContract.Companion.Entrada.NOMBRE_TABLA, null, values)
        //Cerrando conexión
        db.close()



    }
}