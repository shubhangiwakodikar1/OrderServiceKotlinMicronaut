package com.orderserviceinc

import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.Micronaut.*

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .args(*args)
            .packages("com.orderserviceinc")
            .mainClass(Application.javaClass)
            .start()
    }
}

