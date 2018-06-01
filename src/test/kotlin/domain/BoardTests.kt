package domain

import junit.framework.Assert.assertEquals
import org.junit.Test

class BoardTests {

    @Test
    fun shouldCreateBoardWithCardsInTodoColumn_withCountThatMatchesToRequestedCount_WhenInitBoard() {
        val requestedCapacity = 10
        val board = Board.init(requestedCapacity)

        assertEquals(requestedCapacity, board.todoColumn.getCards().size)
    }
}