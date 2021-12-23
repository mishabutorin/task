import org.junit.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.Assert.*

class RobotKtTest {

    @Test
    fun robotInMaze() {
        assertEquals("ddr", robotInMaze("src/main/resources/Maze1"))
        assertEquals("dddrruuu", robotInMaze("src/main/resources/Maze2"))
        assertEquals("dduullddddrr", robotInMaze("src/main/resources/Maze3"))
        assertThrows(IllegalArgumentException("I can't move")::class.java) { robotInMaze("src/main/resources/Maze4") }
        assertThrows(IllegalArgumentException("I can't move")::class.java) { robotInMaze("src/main/resources/Maze5") }
    }
}