fun main() {
    val listOfNumbers = MutableList(readln().toInt()) { readln().toInt() }
    repeat(readln().toInt() % listOfNumbers.size) {
        listOfNumbers.add(0, listOfNumbers.last())
        listOfNumbers.removeLast()
    }
    print(listOfNumbers.joinToString(" "))
}