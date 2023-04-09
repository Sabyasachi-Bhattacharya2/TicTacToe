package tictactoe

import java.lang.NumberFormatException

fun main() {
    // write your code here
    var str = "_________"
    val mutGrid = MutableList(3) { MutableList(3) {'O'} }
    var index = 1
    var ch: Char
    readTheTickTackToe(mutGrid, str)
    printTheTickTackTowGrid(mutGrid)
    do {
        ch = if (index % 2 == 1) 'X' else 'O'
        do {
            val st = readTheTickTackToe(str, ch)
            if (st != "0") {
                readTheTickTackToe(mutGrid, st)
                printTheTickTackTowGrid(mutGrid)
                str = st
            }

        } while (st == "0")
        index++
        if (winLoseEtc(mutGrid) == "X wins" || winLoseEtc(mutGrid) == "O wins") {
            break
        }
    } while (index <= 9)

    println(winLoseEtc(mutGrid))
}
fun readTheTickTackToe (mutGrid: MutableList<MutableList<Char>>, str: String) {
    var ind = 0
    for (i in 0 until mutGrid[0].size) {
        for (j in 0 until mutGrid.size) {
            mutGrid[i][j] = str[ind]
            ind++
        }
    }
}
fun readTheTickTackToe (str: String, char: Char): String{
    try {
        val (a, b) = readln().split(" ").map { it.toInt() }
        if (a>3 || b > 3) {
            println("Coordinates should be from 1 to 3!")
            return "0"
        }
        val pos = ((a - 1) * 3) + (b - 1)
        if (str[pos] == 'X' || str[pos] == 'O') {
            println("This cell is occupied! Choose another one!")
            return "0"
        }
        return str.substring(0, pos) + char + str.substring(pos + 1)
    } catch (e: NumberFormatException) {
        println("You should enter numbers!")
        return "0"
    }
}
fun printTheTickTackTowGrid (mutGrid: MutableList<MutableList<Char>>){
    println("---------")
    for (i in 0 until  mutGrid[0].size) {
        print("| ")
        for (j in 0 until mutGrid.size) {
            if (mutGrid[i][j] == '_') {
                print("  ")
                continue
            }
            print("${mutGrid[i][j]} ")
        }
        print("|\n")
    }
    println("---------")
}
fun winLoseEtc (mutGrid: MutableList<MutableList<Char>>): String {
    var countX = 0
    var countO = 0
    var countBlank = 0
    for (i in 0 until mutGrid[0].size) {
        for (j in 0 until mutGrid.size) {
            if (mutGrid[i][j]=='X')
                countX++
            else if (mutGrid[i][j] == 'O')
                countO++
            else
                countBlank++
        }
    }
    return if (Math.abs(countX - countO) > 1 || winningCriteria(mutGrid, 'X') && winningCriteria(mutGrid, 'O')) {
        "Impossible"
    } else if (winningCriteria(mutGrid, 'X')) {
        "X wins"
    } else if (winningCriteria(mutGrid, 'O')) {
        "O wins"
    } else if (!winningCriteria(mutGrid, 'X') && !winningCriteria(mutGrid,'O') && countBlank == 0) {
        "Draw"
    } else {
        "Game not finished"
    }

}

// Calculates the Boolean value for X/O win (true or false)
fun winningCriteria (mutGrid: MutableList<MutableList<Char>>, c: Char): Boolean {
    return if (mutGrid[0][0] == c && mutGrid[0][1] == c && mutGrid[0][2] == c
        || mutGrid[1][0] == c && mutGrid[1][1] == c && mutGrid[1][2] == c
        || mutGrid[2][0] == c && mutGrid[2][1] == c && mutGrid[2][2] == c){
        true
    } else if (mutGrid[0][0] == c && mutGrid[1][0] == c && mutGrid[2][0] == c
        || mutGrid[0][1] == c && mutGrid[1][1] == c && mutGrid[2][1] == c
        || mutGrid[0][2] == c && mutGrid[1][2] == c && mutGrid[2][2] == c) {
        true
    } else if (mutGrid[0][0] == c && mutGrid[1][1] == c && mutGrid[2][2] == c
        || mutGrid[0][2] == c && mutGrid[1][1] == c && mutGrid[2][0] == c) {
        true
    } else {
        false
    }
}