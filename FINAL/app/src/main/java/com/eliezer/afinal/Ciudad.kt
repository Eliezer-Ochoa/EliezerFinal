package com.eliezer.afinal

class Ciudad (name:String, weather:List<Weather>, main:Main) {
    var name:String = ""
    var weather:List<Weather>? = null
    var main:Main? = null
    init {
        this.name = name
        this.weather = weather
        this.main = main
    }

}
