import org.junit.Test

import org.junit.Assert.*

class RobotKtTest {

    @Test
    fun robotInMaze() {
        assertEquals("ddr", robotInMaze("src/main/resources/Maze1"))
        assertEquals("dddrruuu", robotInMaze("src/main/resources/Maze2"))
        assertEquals("dduullddddrr", robotInMaze("src/main/resources/Maze3"))
            }
}