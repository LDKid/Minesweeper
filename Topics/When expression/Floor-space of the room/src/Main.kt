import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    when (readln()) {
        "rectangle" -> print(readln().toDouble() * readln().toDouble())
        "triangle" -> {
            val a = readln().toDouble()
            val b = readln().toDouble()
            val c = readln().toDouble()
            // Heron's formula
            val s = (a + b + c) / 2
            print(sqrt(s * (s - a) * (s - b) * (s - c)))
        }
        "circle" -> print(3.14 * readln().toDouble().pow(2))
    }
}