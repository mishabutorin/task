import java.io.File
import kotlin.IndexOutOfBoundsException

/**
Дан текстовый файл, в котором схематично изображена схема прямоугольного мини-лабиринта:
- во всех строках одинаковое количество символов
- символ # обозначает препятствие, символ . свободное место, символ * начальное местоположение "Робота",
символ ^ местоположение "цели"

Функция, которую нужно написать, принимает как параметр имя этого файла.
Она должна вернуть как результат строку с командами для робота вида "rllluddurld",
где r обозначает движение на клетку вправо, l влево, u вверх и d вниз, такую, чтобы робот,
исполнив эти команды, прошёл от своего начального местоположения до цели (на препятствия наступать нельзя).
В случае, если подобный проход невозможен, следует бросить исключение (любое на ваш выбор). */
fun offset(current: Pair<Int, Int>, delta: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(current.first + delta.first, current.second + delta.second)
}

fun robotInMaze(inputName: String): String {
    val allowed = mutableSetOf<Pair<Int, Int>>()
    var way = ""  //результат
    val inputFile = File(inputName).readLines() //импортируемый файл
    var start = Pair(0, 0)
    var end = Pair(0, 0)
    for ((y, str) in inputFile.withIndex()) { //прохождение по всем сивмолам файла
        for ((x, symbol) in str.withIndex()) {
            if (symbol == '.') {
                allowed.add(Pair(x, y))
            } else if (symbol == '*') {
                start = Pair(x, y)
                allowed.add(start)
            } else if (symbol == '^') {
                end = Pair(x, y)
                allowed.add(end)
            }
        }
    }
    val mazeSize = inputFile.size * inputFile[0].length
    val downDelta = Pair(0, +1)
    val rightDelta = Pair(+1, 0)
    val upDelta = Pair(0, -1)
    val leftDelta = Pair(-1, 0)
    while (start != end) {
        while ((offset(start, downDelta) in allowed) && (start != end)) {
            start = offset(start, downDelta)
            way += 'd'
        }
        while ((offset(start, rightDelta) in allowed) && (start != end)) {
            start = offset(start, rightDelta)
            way += 'r'
        }
        while ((offset(start, upDelta) in allowed) && (start != end)) {
            start = offset(start, upDelta)
            way += 'u'
        }
        while ((offset(start, leftDelta) in allowed) && (start != end)) {
            start = offset(start, leftDelta)
            way += 'l'
        }
        if ((offset(start, downDelta) !in allowed && offset(start, rightDelta) !in allowed && offset(start, upDelta) !in allowed && offset(start, leftDelta) !in allowed) || (way.length > mazeSize)) {
            throw IllegalArgumentException("I can't move")
        }

    }
    return way
}

fun main() {
    println(robotInMaze("src/main/resources/Maze1"))
    println(robotInMaze("src/main/resources/Maze2"))
    println(robotInMaze("src/main/resources/Maze3"))
    println(robotInMaze("src/main/resources/Maze4"))
    println(robotInMaze("src/main/resources/Maze5"))
}