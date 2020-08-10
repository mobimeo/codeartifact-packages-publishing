package com.reachnow

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import com.reachnow.MyLibrary

@Controller("/hello") 
class HelloController {
    val lib = MyLibrary()

    @Get("/")  
    @Produces(MediaType.TEXT_PLAIN) 
    fun index(): String {
        return lib.busVehicle().name;
    }
}
