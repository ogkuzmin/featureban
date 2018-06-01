package domain

import domain.dsl.Create
import junit.framework.Assert.*
import org.junit.Test

class ColumnTests {

    @Test
    fun shouldReturnTrue_ifColumnContainsCardWithOwnerThatMatchesToPlayer_whenCheckContainingCardsOfPlayer() {
        val player = Create
                .player()
                .please()
        val card = Create
                .card()
                .withOwner(player)
                .please()
        val column = Create
                .column()
                .withCard(card)
                .please()

        assertTrue(column.containsCardOf(player))
    }

    @Test
    fun shouldReturnFalse_ifColumnContainsCardWithOwnerThatDoesNotMatchToPlayer_whenCheckContainingCardsOfPlayer() {
        val firstPlayer = Create
                .player()
                .please()
        val secondPlayer = Create
                .player()
                .please()
        val card = Create
                .card()
                .withOwner(firstPlayer)
                .please()
        val column = Create
                .column()
                .withCard(card)
                .please()

        assertFalse(column.containsCardOf(secondPlayer))
    }

    @Test
    fun shouldReturnFalse_forColumnWithoutAnyCard_whenCheckContainingCardsOfPlayer() {
        val player = Create
                .player()
                .please()
        val column = Create
                .column()
                .please()

        assertFalse(column.containsCardOf(player))
    }

    @Test
    fun shouldReturnListOfCard_thatMatchesToAddedCards_whenGetCards() {
        val firstCard = Create
                .card()
                .please()
        val secondCard = Create
                .card()
                .please()
        val thirdCard = Create
                .card()
                .please()
        val column = Create
                .column()
                .please()


        column.add(firstCard)
        column.add(secondCard)
        column.add(thirdCard)

        assertEquals(column.getCards(), listOf(firstCard, secondCard, thirdCard))
    }
}