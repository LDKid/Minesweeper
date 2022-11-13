fun main() {
    val numbers = MutableList(readLine()!!.toInt()) { readLine()!!.toInt() }
    val pm = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    println(if (numbers.containsAll(pm)) "YES" else "NO")
}