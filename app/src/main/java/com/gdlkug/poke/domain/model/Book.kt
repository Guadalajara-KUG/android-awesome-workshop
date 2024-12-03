package com.gdlkug.poke.domain.model

import com.gdlkug.poke.domain.annotations.ValidateCode
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    @ValidateCode("[A-Z]{2}[0-9]{2}")
    val code: String
) {
    init {
        val properties = this::class.memberProperties
        for (property in properties) {
            property.findAnnotation<ValidateCode>()?.let { annotation ->
                val value = property.getter.call(this) as? String
                value?.let {
                    if (!it.matches(annotation.pattern.toRegex())) {
                        throw IllegalArgumentException(
                            "El campo '${property.name}' con valor '$it' no cumple con el patrón '${annotation.pattern}'"
                        )
                    }
                }
            }
        }
    }
}
