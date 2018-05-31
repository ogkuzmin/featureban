package domain

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class CardTests {

    @Test
    fun shouldReturnTrue_forPlayerThatMatchesToCardOwner_whenCheckOwner() {
        var card = Card()
        var player = Player()
        card.owner = player

        assertTrue(card.isOwnedBy(player))
    }

    @Test
    fun shouldReturnFalse_forPlayerThatDoesNotMatchToCardOwner_whenCheckOwner() {
        var card = Card()
        var firstPlayer = Player()
        var secondPlayer = Player()
        card.owner = firstPlayer

        assertFalse(card.isOwnedBy(secondPlayer))
     }

}