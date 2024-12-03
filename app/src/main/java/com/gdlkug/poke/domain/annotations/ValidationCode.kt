package com.gdlkug.poke.domain.annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
// @Repeatable
// @MustBeDocumented
annotation class ValidateCode(val pattern: String)
