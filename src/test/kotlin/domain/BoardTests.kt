package domain

import domain.dsl.Create
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class BoardTests {

    @Test
    fun shouldCreateBoardWithCardsInTodoColumn_withCountThatMatchesToRequestedCount_WhenInitBoard() {
        val requestedCapacity = 10
        val board = Board.init(requestedCapacity)

        assertEquals(requestedCapacity, board.todoColumn.getCards().size)
    }

    @Test
    fun shouldReturnCard_ThatIsContainedInProgressColumn_whenMoveInProgress() {
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()

        val todoCard = board.moveToProgress()

        assertTrue(board.inProgressColumn.getCards().contains(todoCard))
    }
}