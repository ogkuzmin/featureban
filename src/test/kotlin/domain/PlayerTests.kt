package domain

import domain.dsl.Create
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class PlayerTests {

    @Test
    fun shouldReturnFalse_ifCoinFlippedWithHeads_whenPlay() {
        val player = Create.player()
                .please()

        val coin = getCoinWithAlways(CoinSide.HEADS)

        assertFalse(player.play(coin))
    }

    @Test
    fun shouldReturnTrue_ifCoinFlippedWithTails_whenPlay() {
        val player = Create.player()
                .please()
        val coin = getCoinWithAlways(CoinSide.TAILS)

        assertTrue(player.play(coin))
    }

    @Test
    fun shouldMoveToDoneColumn_cardThatOwnedByHimAndItIsNotBlockedFromVerificationColumnIfHeWins_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .please())
        val card = board.moveToProgress(player)
        board.moveToVerification(card!!)

        player.playOn(board, coin)

        verify(board).moveToDone(card)
    }

    @Test
    fun shouldMoveToVerificationColumn_cardThatOwnedByHimIfHeWinsAndWipLimitOfVerificationColumnIsNotSpentAndCardIsNotBlocked_whenPlayOnBoard() {
        val limitWip = 3
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(limitWip)
                .please())
        val card = board.moveToProgress(player)

        player.playOn(board, coin)

        verify(board).moveToVerification(card!!)
    }

    @Test
    fun shouldUnblockCardInVerificationColumn_cardThatOwnedByHimIfHeWinsAndThereIsNotAnyNonBlockedCardOfThisPlayer_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .please())
        val card = board.moveToProgress(player)
        board.moveToVerification(card!!)
        card.isBlocked = true

        player.playOn(board, coin)

        assertFalse(card.isBlocked)
    }

    @Test
    fun shouldUnblockCardInInProgressColumn_cardThatOwnedByHimIfHeWinsAndInProgressColumnDoesNotContainAnyOtherCardOfThisPlayer_whenPlayOnBoard() {
        val limitWip = 2
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(limitWip)
                .please()
        val card = board.moveToProgress(player)
        card!!.isBlocked = true

        player.playOn(board, coin)

        assertFalse(card.isBlocked)
    }

    @Test
    fun shouldMoveNewCardToInProgressColumn_ifHeWinsAndThereIsNotAnyHisCardInVerificationOrInProgressColumns_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .please())

        player.playOn(board, coin)

        verify(board).moveToProgress(player)
    }

    @Test
    fun shouldMoveToDoneColumn_cardOfAnotherPlayerIfHeWinsAndThereIsNotAnyHisCardInVerificationOrInProgressColumnsAndWipLimitOfInProgressColumnIsSpent_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val firstPlayer = Create
                .player()
                .please()
        val secondPlayer = Create
                .player()
                .please()
        val wipLimit = 1
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(wipLimit)
                .please())
        val assertionCard = board.moveToProgress(firstPlayer)
        board.moveToVerification(assertionCard!!)
        board.moveToProgress(firstPlayer)

        secondPlayer.playOn(board, coin)

        verify(board).moveToDone(assertionCard)
    }

    @Test
    fun shouldMoveToVerificationColumn_cardOfAnotherPlayerIfHeWinsAndThereIsNotAnyHisCardInVerificationOrInProgressColumnsAndInVerificationColumnIsEmptyAndWipLimitOfInProgressColumnIsSpent_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val firstPlayer = Create
                .player()
                .please()
        val secondPlayer = Create
                .player()
                .please()
        val wipLimit = 1
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(wipLimit)
                .please())
        val assertionCard = board.moveToProgress(firstPlayer)

        secondPlayer.playOn(board, coin)

        verify(board).moveToVerification(assertionCard!!)
    }

    @Test
    fun shouldUnblockCardInVerificationColumn_ofAnotherPlayerIfHeWinsAndThereIsNotAnyHisCardInVerificationOrInProgressColumnsAndVerificationColumnContainsBlockedCardOfAnotherPlayerAndWipLimitOfInProgressColumnIsSpent_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val firstPlayer = Create
                .player()
                .please()
        val secondPlayer = Create
                .player()
                .please()
        val wipLimit = 1
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(wipLimit)
                .please())
        val assertionCard = board.moveToProgress(firstPlayer)
        board.moveToVerification(assertionCard!!)
        assertionCard.isBlocked = true
        board.moveToProgress(firstPlayer)

        secondPlayer.playOn(board, coin)

        assertFalse(assertionCard.isBlocked)
    }

    @Test
    fun shouldUnblockCardInProgressColumn_ofAnotherPlayerIfHeWinsAndThereIsNotAnyHisCardInInProgressColumnAndInVerificationColumnIsEmptyAndWipLimitOfInProgressColumnIsSpent_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val firstPlayer = Create
                .player()
                .please()
        val secondPlayer = Create
                .player()
                .please()
        val wipLimit = 1
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(wipLimit)
                .please())
        val assertionCard = board.moveToProgress(firstPlayer)
        assertionCard!!.isBlocked = true

        secondPlayer.playOn(board, coin)

        assertFalse(assertionCard.isBlocked)
    }

    @Test
    fun shouldBlockCardInVerificationColumn_thatOwnedByHimIfHeLose_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.HEADS)
        val player = Create
                .player()
                .please()
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()
        val assertionCard = board.moveToProgress(player)
        board.moveToVerification(assertionCard!!)

        player.playOn(board, coin)

        assertTrue(assertionCard.isBlocked)
    }

    @Test
    fun shouldBlockCardInInProgressColumn_thatOwnedByHimIfHeLoseAndVerificationColumnDoesNotContainNonBlockedCardOfThisPlayer_whenPlayOnBoard() {
        val coin = getCoinWithAlways(CoinSide.HEADS)
        val player = Create
                .player()
                .please()
        val board = Create
                .board()
                .withTodoCapacity(10)
                .please()
        val firstCard = board.moveToProgress(player)
        board.moveToVerification(firstCard!!)
        firstCard.isBlocked = true
        val assertionCard = board.moveToProgress(player)

        player.playOn(board, coin)

        assertTrue(assertionCard!!.isBlocked)
    }

    private fun getCoinWithAlways(coinSide: CoinSide): Coin {
        val coin = Mockito.spy(Coin())
        `when`(coin.flip()).thenReturn(coinSide)
        return coin
    }
}