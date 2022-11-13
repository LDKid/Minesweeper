const val MIN_TEMP = -92
const val MAX_TEMP = 57
const val MOSCOW_AVG = 5
const val HANOI_AVG = 20
const val DUBAI_AVG = 30

data class City(val name: String) {
    var degrees: Int = 0
        set(value) {
            field = if (value in MIN_TEMP..MAX_TEMP) value else {
                when (name) {
                    "Moscow" -> MOSCOW_AVG
                    "Hanoi" -> HANOI_AVG
                    else -> DUBAI_AVG
                }
            }
        }
}        

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    print(
        when {
            firstCity.degrees < secondCity.degrees && firstCity.degrees < thirdCity.degrees -> firstCity.name
            secondCity.degrees < firstCity.degrees && secondCity.degrees < thirdCity.degrees -> secondCity.name
            thirdCity.degrees < firstCity.degrees && thirdCity.degrees < secondCity.degrees -> thirdCity.name
            else -> "neither"
        }
    )
}