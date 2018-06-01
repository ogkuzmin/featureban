package domain

import domain.dsl.Create
import junit.framework.Assert.*
import org.junit.Test

class BoardTests {

    @Test
    fun shouldCreateBoardWithCardsInTodoColumn_withCountThatMatchesToRequestedCount_WhenInitBoard() {
        val requestedCapacity = 10
        val board = Board.init(requestedCapacity)

        assertEquals(requestedCapacity, board.todoColumn.cards().size)
    }

    @Test
    fun shouldReturnCard_ThatIsContainedInProgressColumn_whenMoveInProgress() {
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()

        val todoCard = board.moveToProgress()

        assertTrue(board.inProgressColumn.cards().contains(todoCard))
    }

    @Test
    fun shouldReturnFalse_ForCardMovedToInProgress_whenCheckTodoColumnForContainingThisCard() {
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()

        val todoCard = board.moveToProgress()

        assertFalse(board.todoColumn.cards().contains(todoCard))
    }

    @Test
    fun shouldReturnNull_ifTodoColumnIsEmpty_whenMoveInProgress() {
        val capacity = 5
        val board = Create
                .board()
                .withTodoCapacity(capacity)
                .please()

        for (i in 0..capacity) {
            board.moveToProgress()
        }
        val nullResult = board.moveToProgress()

        assertNull(nullResult)
    }

    @Test
    fun shouldReturnTrue_afterCardWasMovedToVerification_whenCheckVerificationColumnForContainingThisCard() {
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()
        val card = board.moveToProgress()

        board.moveToVerification(card!!)

        assertTrue(board.verificationColumn.cards().contains(card))
    }

    @Test
    fun shouldReturnFalse_afterCardWasMovedToVerification_whenCheckInProgressColumnForContainingThisCard() {
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()
        val card = board.moveToProgress()

        board.moveToVerification(card!!)

        assertFalse(board.inProgressColumn.cards().contains(card))
    }
}