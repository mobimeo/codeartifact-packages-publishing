package com.reachnow

import org.junit.Assert.assertEquals
import org.junit.Test

class MyLibraryTest {
    @Test fun testMyVehicle() {
        assertEquals("Bus", MyLibrary().busVehicle().name)
        assertEquals(5, MyLibrary().busVehicle().speed)
    }
}
