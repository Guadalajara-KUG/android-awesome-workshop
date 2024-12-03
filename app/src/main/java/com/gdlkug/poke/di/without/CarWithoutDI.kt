package com.gdlkug.poke.di.without

class CarWithoutDI {
    private val engine = EngineWithoutDI()

    fun drive() {
        println(engine.start())
        println("Car is driving!")
    }
}

class EngineWithoutDI{
    fun start() = "Engine started!"
}