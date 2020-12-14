package com.eliezer.afinal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class EquiposCRUD(context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    //crear nuevo equipo
    fun newEquipos(item: Equipos) {
        //creación de una neuva BD
        //Definiendo el obejeto de BD que se referncia a helper, ejcuta exectSQL DatBase Helper
        //Abrir BD modo escritura
        val db: SQLiteDatabase =
            helper?.writableDatabase!!  //writableDatabasehabilitar BD para poder escribir en ella

        //Llenando los datos que queremos insertar en nuestra tabla
        // Clase que me permite empexzar agrupar información para poder mapiarla con mi tabla
        val values = ContentValues()

        //Mapeo de las columnas con valores a insertar
        values.put(EquiposContract.Companion.Entrada.COLUMNA_ID, item.id)
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

    //Traera todos los elementospara mostrar en la interfazde la aplicación
    //Generar consulta a nuestra BD
    fun getEquipos(): ArrayList<Equipos> {
        //definir nuestra BD

        val items: ArrayList<Equipos> = ArrayList()
        //Abrir BD en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especuifuicar las columnas que quiero consultar
        val columnas = arrayOf(
            EquiposContract.Companion.Entrada.COLUMNA_ID,
            EquiposContract.Companion.Entrada.COLUMNA_NOMBRE,
            EquiposContract.Companion.Entrada.COLUMNA_MARCA,
            EquiposContract.Companion.Entrada.COLUMNA_MODELO,
            EquiposContract.Companion.Entrada.COLUMNA_SERIE,
            EquiposContract.Companion.Entrada.COLUMNA_PRECIO,
            EquiposContract.Companion.Entrada.COLUMNA_ESTADO,
            EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA,
            EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD
        )

        //Crear un curso para recorrer la tabla
        val c:Cursor = db.query(
            EquiposContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )
        //Hacer el recorrido en la tabla
        while (c.moveToNext()) {
            items.add(
                Equipos(
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_MARCA)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_MODELO)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_SERIE)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_PRECIO)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_ESTADO)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA)),
                    c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD))

                )
            )

        }
        //Cerrar DataBase
        db.close()

        return items
    }

    //Haciendo un filtro de consultas con el mismo query
    fun getEquipos(id:String): Equipos {
        var item:Equipos? = null

        val db:SQLiteDatabase = helper?.readableDatabase!!
        val columnas = arrayOf(
            EquiposContract.Companion.Entrada.COLUMNA_ID,
            EquiposContract.Companion.Entrada.COLUMNA_NOMBRE,
            EquiposContract.Companion.Entrada.COLUMNA_MARCA,
            EquiposContract.Companion.Entrada.COLUMNA_MODELO,
            EquiposContract.Companion.Entrada.COLUMNA_SERIE,
            EquiposContract.Companion.Entrada.COLUMNA_PRECIO,
            EquiposContract.Companion.Entrada.COLUMNA_ESTADO,
            EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA,
            EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD)


        val c:Cursor = db.query(
            EquiposContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            "id = ?",
            arrayOf(id),
            null,
            null,
            null

        )

        while(c.moveToNext()) {
            item = Equipos(
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_NOMBRE)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_MARCA)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_MODELO)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_SERIE)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_PRECIO)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_ESTADO)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA)),
                c.getString(c.getColumnIndexOrThrow(EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD)))


        }
        //Sliendo del while
        c.close()

        return item!!;

    }
    fun updateEquipos(item: Equipos){
        //Abrir BD en modo escritura
        //Cambio de datos y mapiando
        val db:SQLiteDatabase = helper?.writableDatabase!!
        val values = ContentValues()
        values.put(EquiposContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_MARCA, item.marca)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_MODELO, item.modelo)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_SERIE, item.serie)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_PRECIO, item.precio)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_ESTADO, item.estado)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_FECHACOMPRA, item.fechacompra)
        values.put(EquiposContract.Companion.Entrada.COLUMNA_CANTIDAD, item.cantidad)

        //Ejecutar BD
        db.update(
            EquiposContract.Companion.Entrada.NOMBRE_TABLA,
            values, "id = ?",
        arrayOf(item.id))

        db.close()

    }

}

