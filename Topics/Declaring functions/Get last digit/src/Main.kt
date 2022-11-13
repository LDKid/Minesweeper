fun getLastDigit(num: Int) = kotlin.math.abs(num % 10)

/* Do not change code below */
fun main() {
    val a = readLine()!!.toInt()
    println(getLastDigit(a))
}