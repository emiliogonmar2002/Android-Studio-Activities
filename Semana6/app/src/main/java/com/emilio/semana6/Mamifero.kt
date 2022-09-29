package com.emilio.semana6

import android.util.Log

// definicion de clases en kotlin
// se puede tener una clase vacia
// class Mamifero


// como parte de la definicion se incluyen los parametros
// del constructr principal

// 2 opciones para los parametros de constructor principal
// 1 - son solo parametros
// 2 - se reciben como propiedad

// clase abstracta
// - un tipo del que no se puede instancias
// - pueden tener metodos abstractos
open class Mamifero (edad: Int, nombre: String) {

    // se puede inicializar propiedades
    var edadProp = edad
    var peso = 0f
    var color = ""

    // se pueden incluir cualquier cantidad de bloques de inicializacion
    init {

        Log.wtf("PRUEBA CLASES", edadProp.toString())
    }


    // se pueden intercalar bloques de inicializacion con declaracion de propiedades indistintamente

    var nombreProp = nombre

    init {
        Log.wtf("PRUEBA CLASES", nombreProp.toString())
    }

    // ademas del principal podemos tener cualquier cantidad de constructres secundarios
    // se distinguen por tener una lista de parametros distinta
    // todos los constructores secundarios se llaman constructor
    constructor(edad: Int, nombre: String, peso: Float): this(edad, nombre) {
        this.peso = peso
        Log.wtf("PRUEBA CLASES", "constructor secundario")
    }

    constructor(
        edad: Int,
        nombre: String,
        peso: Float,
        color: String
    ) : this(edad, nombre, peso) {
        this.color = color
        Log.wtf("PRUEBA CLASES", "constructor secundario 2")
    }

    open fun saludar() {
        Log.wtf("PRUEBA CLASES", "CONSTRUCTOR SECUNDARIO PERRITO")
    }

    abstract fun comer()
}