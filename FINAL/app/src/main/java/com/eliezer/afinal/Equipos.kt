package com.eliezer.afinal

class Equipos(codigo:String, nombre:String, marca:String, modelo:String, serie:String, precio:String, estado:String, fechacompra:String, cantidad:String) {
//Creación de clase para nuestros campos de tabla de la BD
    var id: String? = null;
    var nombre: String? = null;
    var marca: String? = null;
    var modelo: String? = null;
    var serie: String? = null;
    var precio: String? = null;
    var estado: String? = null;
    var fechacompra: String? = null;
    var cantidad: String? = null;

    init{
        this.id = codigo;
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