package com.reachnow

import com.reachnow.main
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun testRootRequest() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("hi", response.content)
        }
    }
    @Test
    fun testLibRequests() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/lib")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Kotlin", response.content)
        }
    }
}
