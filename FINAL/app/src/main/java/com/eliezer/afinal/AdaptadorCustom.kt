package com.eliezer.afinal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(items:ArrayList<Equipos>, var listener:ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items: ArrayList<Equipos>? = null
    var multiSeleccion = false

    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.templat_eequipos, parent, false)
        viewHolder = ViewHolder(vista, listener, longClickListener)

        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.nombre?.text = item?.nombre
        holder.id?.text = item?.id
        holder.marca?.text = item?.marca
        holder.modelo?.text = item?.modelo
        holder.serie?.text = item?.serie
        holder.precio?.text = item?.precio
        holder.estado?.text = item?.estado
        holder.fechacompra?.text = item?.fechacompra
        holder.cantidad?.text = item?.cantidad


        if (itemsSeleccionados?.contains(position)!!) {
            holder.vista.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode() {
        multiSeleccion = true
    }

    fun destruirActionMode() {
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode() {
        // eliminar elementos seleccionados
        for (item in itemsSeleccionados!!) {
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index: Int) {
        if (multiSeleccion) {
            if (itemsSeleccionados?.contains(index)!!) {
                itemsSeleccionados?.remove(index)
            } else {
                itemsSeleccionados?.add(index)
            }

            notifyDataSetChanged()
        }
    }

    fun obtenerNumeroElementosSeleccionados(): Int {
        return itemsSeleccionados?.count()!!
    }

    fun eliminarSeleccionados() {
        if (itemsSeleccionados?.count()!! > 0) {
            var itemsEliminados = ArrayList<Equipos>()

            for (index in itemsSeleccionados!!) {
                itemsEliminados.add(items?.get(index)!!)
            }

            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }


    class ViewHolder(vista: View, listener: ClickListener, longClickListener: LongClickListener) : RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener {

        var vista = vista

        var nombre: TextView? = null
        var id: TextView? = null
        var marca: TextView? = null
        var modelo: TextView? = null
        var serie: TextView? = null
        var precio: TextView? = null
        var estado: TextView? = null
        var fechacompra: TextView? = null
        var cantidad: TextView? = null
        var listener: ClickListener? = null
        var longListener: LongClickListener? = null

        init {

            nombre = vista.findViewById(R.id.textnombre)
            id = vista.findViewById(R.id.editcodigo)
            marca = vista.findViewById(R.id.textmarca)
            modelo = vista.findViewById(R.id.textmodelo)
            serie = vista.findViewById(R.id.textserie)
            precio = vista.findViewById(R.id.textprecio)
            estado = vista.findViewById(R.id.textestado)
            fechacompra = vista.findViewById(R.id.textfechacompra)
            cantidad = vista.findViewById(R.id.textcantidad)



            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }


    }
}