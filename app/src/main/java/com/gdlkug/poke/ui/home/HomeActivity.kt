package com.gdlkug.poke.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.gdlkug.poke.di.with.CarWithDI
import com.gdlkug.poke.di.with.Engine
import com.gdlkug.poke.di.without.CarWithoutDI

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // --------------------------------
        //Without Dependency Injection
        val carWithoutDI = CarWithoutDI()
        carWithoutDI.drive()
        // -----------------------------


        // --------------------------------
        //With Dependency Injection
        val engine = Engine()
        val carWithDI = CarWithDI(engine)
        carWithDI.drive()
        // -----------------------------
    }
}
