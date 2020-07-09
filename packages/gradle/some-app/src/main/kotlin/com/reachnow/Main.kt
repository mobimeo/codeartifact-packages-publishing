package com.reachnow

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import com.reachnow.MyLibrary

/**
 * simple ktor web app serving 2 routes
 */
fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("hi", ContentType.Text.Html)
        }
        get("/lib"){
            val myLib = MyLibrary()
            call.respondText( myLib.kotlinLanguage().name, ContentType.Text.Html)
        }
    }
}
