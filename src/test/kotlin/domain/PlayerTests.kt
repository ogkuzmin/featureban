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
    fun shouldMoveToVerificationColumn_cardThatOwnedByHimIfHeWinsAndWipLimitOfVerificationColumnIsNotSpent_whenPlayOnBoard() {
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
        val card = board.moveToProgress()
        card!!.owner = player

        player.playOn(board, coin)

        verify(board).moveToVerification(card)
    }

    @Test
    fun shouldUnblockCardInInProgressColumn_cardThatOwnedByHimIfHeWinsAndWipLimitOfVerificationColumnIsSpent_whenPlayOnBoard() {
        val limitWip = 2
        val coin = getCoinWithAlways(CoinSide.TAILS)
        val player = Create
                .player()
                .please()
        val board = Mockito.spy(Create
                .board()
                .withTodoCapacity(10)
                .withWipLimit(limitWip)
                .please())
        val cardFirst = board.moveToProgress()
        board.moveToVerification(cardFirst!!)
        val cardSecond = board.moveToProgress()
        board.moveToVerification(cardSecond!!)
        val thirdCard = board.moveToProgress()
        thirdCard!!.owner = player
        thirdCard.isBlocked = true

        player.playOn(board, coin)

        assertFalse(thirdCard.isBlocked)
    }

    private fun getCoinWithAlways(coinSide: CoinSide): Coin {
        val coin = Mockito.spy(Coin())
        `when`(coin.flip()).thenReturn(coinSide)
        return coin
    }
}