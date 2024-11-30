package com.gdlkug.poke.util.extension

fun <T> T?.default(def: T): T = this ?: def
