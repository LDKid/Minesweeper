package minesweeper

import kotlin.random.Random

const val FIELD_SIZE = 9

enum class GameSymbols(val symbol: Char) {
    MINE('X'),
    MARKED('*'),
    UNKNOWN('.'),
    SAFE('/');
}
enum class GameCommands(val cmd: String) {
    MARK("mine"),
    EXPLORE("free");
}
enum class GameStates {
    OFF,
    PLAYING,
    WON,
    LOST;
}

class Minesweeper(private val fieldSize: Int = FIELD_SIZE) {
    private var field = MutableList(fieldSize) { MutableList(fieldSize) { '0' } }
    private var displayField = MutableList(fieldSize) { MutableList(fieldSize) { GameSymbols.UNKNOWN.symbol } }
    private var mineCount = 0
    private var markedCount = 0
    private var unexploredCount = fieldSize * fieldSize
    private var gameState = GameStates.OFF

    init {
        println("How many mines do you want on the field? ")
        mineCount = readln().toInt()
    }
    private fun generateMines(x: Int, y: Int) {
        repeat(mineCount) {
            var row: Int
            var column: Int
            do {
                row = Random.nextInt(0, fieldSize)
                column = Random.nextInt(0, fieldSize)
            } while (field[row][column] == GameSymbols.MINE.symbol || (x == row && y == column))
            field[row][column] = GameSymbols.MINE.symbol
            increaseAdjacentCellCount(row, column)
        }
    }
    private fun printField() {
        println(" │123456789│")
        println("—│—————————│")
        for (i in 1..fieldSize) {
            val row = displayField[i - 1]
            println("$i|${row.joinToString("")}|")
        }
        println("—│—————————│")
    }
    private fun increaseAdjacentCellCount(row: Int, column: Int) {
        for (i in -1..1) {
            for (j in -1..1) {
                val thisRow = row + i
                val thisColumn = column + j
                if (thisRow in 0 until fieldSize && thisColumn in 0 until fieldSize) {
                    if (field[thisRow][thisColumn].isDigit()) {
                        field[thisRow][thisColumn] = (field[thisRow][thisColumn].code + 1).toChar()
                    }
                }
            }
        }
    }
    fun play() {
        gameState = GameStates.PLAYING
        printField()
        while (gameState == GameStates.PLAYING) {
            handleInput() // Lose here
            printField()
            if (checkVictory()) gameState = GameStates.WON
        }
        when (gameState) {
            GameStates.WON -> println("Congratulations! You found all the mines!")
            else -> println("You stepped on a mine and failed!")
        }
    }
    private fun handleInput() {
        var validInput = false
        do {
            println("Set/unset mine marks or claim a cell as free: ")
            val userInput = readln().split(' ')
            val y = userInput[0].toInt() - 1
            val x = userInput[1].toInt() - 1
            when(userInput[2]) {
                GameCommands.MARK.cmd -> {
                    when(displayField[x][y]) {
                        GameSymbols.UNKNOWN.symbol -> {
                            validInput = true
                            displayField[x][y] = GameSymbols.MARKED.symbol
                            markedCount++
                        }
                        GameSymbols.MARKED.symbol -> {
                            validInput = true
                            displayField[x][y] = GameSymbols.UNKNOWN.symbol
                            markedCount--
                        }
                        else -> println("There is a number here!")
                    }
                }
                GameCommands.EXPLORE.cmd -> {
                    if (displayField[x][y] == GameSymbols.UNKNOWN.symbol) {
                        validInput = true
                        if (unexploredCount == fieldSize * fieldSize) generateMines(x, y)
                        exploreCell(x, y)
                    }
                }
            }
        } while (!validInput)
    }
    
    private fun checkVictory(): Boolean {
        if (mineCount in setOf(markedCount, unexploredCount)) {
            var allMinesFound = true
            mainLoop@ for (i in 0 until field.size ) {
                for (j in 0 until field[i].size) {
                    if (field[i][j] == GameSymbols.MINE.symbol && displayField[i][j] !in setOf(GameSymbols.MARKED.symbol, GameSymbols.UNKNOWN.symbol)) {
                        allMinesFound = false
                        break@mainLoop
                    }
                }
            }
            return allMinesFound
        }
        return false
    }
    private fun exploreCell(x: Int, y: Int) {
        if (displayField[x][y] == GameSymbols.MARKED.symbol) markedCount--
        unexploredCount--
        when (field[x][y]) {
            GameSymbols.MINE.symbol -> return loseGame()
            '0' -> floodFill(x, y)
            else -> displayField[x][y] = field[x][y]
        }
    }
    private fun floodFill(x: Int, y: Int) {
        displayField[x][y] = GameSymbols.SAFE.symbol
        for (i in x-1..x+1) {
            for (j in y-1..y+1) {
                if (i in 0 until field.size && j in 0 until field.size && displayField[i][j] in setOf(GameSymbols.UNKNOWN.symbol, GameSymbols.MARKED.symbol)) {
                    exploreCell(i, j)
                }
            }
        }
    }
    private fun loseGame() {
        gameState = GameStates.LOST
        for (x in 0 until field.size) {
            for (y in 0 until field.size) {
                if (field[x][y] == GameSymbols.MINE.symbol) displayField[x][y] = field[x][y]
            }
        }
    }
}

fun main() {
    val game = Minesweeper()
    game.play()
}
