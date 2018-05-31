package domain.dsl

import domain.Card
import domain.Player

class Create {

    companion object {

        fun card(): CardBuilder {
            return CardBuilder()
        }

        fun player(): PlayerBuilder {
            return PlayerBuilder()
        }
    }
}

class CardBuilder {

    private val card = Card()

    fun withOwner(player: Player): CardBuilder {
        card.owner = player
        return this
    }

    fun please(): Card {
        return card
    }
}

class PlayerBuilder {

    private val player = Player()

    fun please(): Player {
        return player
    }
}