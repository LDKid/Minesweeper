const val INITIAL_PRICE = 20_000
const val DECREASE_BY_AGE = 2000
const val KM_THRESHOLD = 10_000
const val DECREASE_BY_KM = 200
const val DEFAULT_SPEED = 120
const val PRICE_BY_KM_H = 100
const val AUTOMATIC_TRANSMISSION_PRICE = 1500

fun carPrice(old: Int = 5, kilometers: Int = 100_000, maximumSpeed: Int = DEFAULT_SPEED, automatic: Boolean = false) {
    var price = INITIAL_PRICE
    // Handle Age
    price -= DECREASE_BY_AGE * old
    // Handle KM
    price -= DECREASE_BY_KM * (kilometers / KM_THRESHOLD)
    // Handle maxSpeed
    price += PRICE_BY_KM_H * (maximumSpeed - DEFAULT_SPEED)
    // Handle auto-transmission
    if (automatic) price += AUTOMATIC_TRANSMISSION_PRICE
    // Print
    print(price)
}