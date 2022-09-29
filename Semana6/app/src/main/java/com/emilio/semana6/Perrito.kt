package com.emilio.semana6

import android.util.Log

class Perrito  (
    edad: Int,
    nombre: String,
    peso: Float,
    var raza: String
) : Mamifero(edad, nombre, peso){

    init {
        Log.wtf("PRUEBA CLASE", "BLOQUE DE INICIALIZACION")
    }

    var ciudad = ""

    constructor(edad:Int,
                nombre: String,
                peso: Float,
                raza: String,
                ciudad: String
    ): this(edad, nombre, peso, raza) {
        this.ciudad = ciudad

        Log.wtf("PRUEBA CLASES", "saludos del mamifero: $nombreProp")
    }

    // sobrecarga de metodos - overload
    // varias funciones con mismo nombre, diferentes argumentos
    // idealmente hacen algo muy parecido entre ellas


    // sobreescritura de metodos - override
    // - refefinir logica para un metodo que heredamos

    override fun saludar() {
        super.saludar()
        Log.wtf("PRUEBA CLASE", "woof woof $nombreProp")
    }

    override fun comer() {
        Log.wtf("PRUEBA CLASE", "woof woof comer como atolondrado")
    }

    override fun pasear() {
        Log.wtf("PRUEBA CLASE", "ESTOY PASEANDO!")
    }
}