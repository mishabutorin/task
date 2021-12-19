import java.io.File

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
    while (start != end) {
        var right = Pair(start.first + 1, start.second)
        var left = Pair(start.first - 1, start.second)
        var up = Pair(start.first, start.second - 1)
        var down = Pair(start.first, start.second + 1)
        while ((down in allowed) && (start != end)) {
            start = down
            down = Pair(start.first, start.second + 1)
            right = Pair(start.first + 1, start.second)
            up = Pair(start.first, start.second - 1)
            left = Pair(start.first - 1, start.second)
            way += 'd'
        }
        while ((right in allowed) && (start != end)) {
            start = right
            right = Pair(start.first + 1, start.second)
            up = Pair(start.first, start.second - 1)
            left = Pair(start.first - 1, start.second)
            way += 'r'
        }
        while ((up in allowed) && (start != end)) {
            start = up
            up = Pair(start.first, start.second - 1)
            left = Pair(start.first - 1, start.second)
            way += 'u'
        }
        while ((left in allowed) && (start != end)) {
            start = left
            left = Pair(start.first - 1, start.second)
            way += 'l'
        }
    }
    return way
}

fun main() {
    println(robotInMaze("src/main/resources/Maze1"))
    println(robotInMaze("src/main/resources/Maze2"))
    println(robotInMaze("src/main/resources/Maze3"))
}