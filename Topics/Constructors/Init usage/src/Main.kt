const val MIN_VALUE = -128
const val MAX_VALUE = 127

fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.time)
}

data class ByteTimer(var time: Int) {
    init {
        if (time < MIN_VALUE) time = MIN_VALUE
        if (time > MAX_VALUE) time = MAX_VALUE
    }
}