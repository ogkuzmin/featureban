package domain

import domain.dsl.Create
import junit.framework.Assert.assertTrue
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
}