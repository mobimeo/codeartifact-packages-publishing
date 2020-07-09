package com.reachnow

/**
 * The `Vehicle` type defines a Vehicle with a name and speed level.
 *
 * @property name The name of the vehicle.
 * @property speed A score from 1 to 10
 */
data class Vehicle(val name: String, val speed: Int)

class MyLibrary {
    /**
     * @return data relating to the Kotlin {@code Vehicle}.
     */
    fun busVehicle() = Vehicle("Bus", 5)
}
