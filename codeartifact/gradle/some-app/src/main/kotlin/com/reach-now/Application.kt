package com.reachnow

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.reachnow")
                .mainClass(Application.javaClass)
                .start()
    }
}
