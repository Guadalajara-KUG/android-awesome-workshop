package com.gdlkug.poke.di.with

class CarWithDI(
    private val engine: Engine
) {
    fun drive() {
        println(engine.start())
        println("Car is driving!")
    }
}

class Engine{
    fun start() = "Engine started!"
}