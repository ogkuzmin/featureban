package domain.dsl

import domain.Coin
import domain.CoinSides
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class PlayerTests {

    @Test
    fun shouldReturnFalse_ifCoinFlippedWithHeads_whenPlay() {
        val player = Create
                .player()
                .please()

        val coin = Mockito.spy(Coin())
        `when`(coin.flip()).thenReturn(CoinSides.HEADS)

        assertFalse(player.play(coin))
    }

    @Test
    fun shouldReturnTrue_ifCoinFlippedWithTails_whenPlay() {
        val player = Create
                .player()
                .please()

        val coin = Mockito.spy(Coin())
        `when`(coin.flip()).thenReturn(CoinSides.TAILS)

        assertTrue(player.play(coin))
    }
}