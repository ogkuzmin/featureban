package domain

import domain.dsl.Create
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class CardTests {

    @Test
    fun shouldReturnTrue_forPlayerThatMatchesToCardOwner_whenCheckOwner() {
        val player = Create.player().please()
        val card = Create.card().withOwner(player).please()

        assertTrue(card.isOwnedBy(player))
    }

    @Test
    fun shouldReturnFalse_forPlayerThatDoesNotMatchToCardOwner_whenCheckOwner() {
        val firstPlayer = Create.player().please()
        val secondPlayer = Create.player().please()
        val card = Create.card().withOwner(firstPlayer).please()

        assertFalse(card.isOwnedBy(secondPlayer))
    }

    @Test
    fun shouldReturnFalse_forAnyPlayerAndCardWithoutOwner_whenCheckOwner() {
        val card = Create.card().please()
        val player = Create.player().please()

        assertFalse(card.isOwnedBy(player))
    }
}