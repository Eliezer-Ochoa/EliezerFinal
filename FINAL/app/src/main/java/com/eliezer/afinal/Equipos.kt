package com.eliezer.afinal

class Equipos(codigo:String, nombre:String, marca:String, modelo:String, serie:String, precio:String, estado:String, fechacompra:String, cantidad:String) {
//Creación de clase para nuestros campos de tabla de la BD
    private var codigo: String? = null;
    private var nombre: String? = null;
    private var marca: String? = null;
    private var modelo: String? = null;
    private var serie: String? = null;
    private var precio: String? = null;
    private var estado: String? = null;
    private var fechacompra: String? = null;
    private var cantidad: String? = null;

    init{
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
        this.precio = precio;
        this.estado = estado;
        this.fechacompra = fechacompra;
        this.cantidad = cantidad;
    }



}