package domain.dsl

import domain.Card
import domain.Column
import domain.Player

class Create {

    companion object {

        fun card(): CardBuilder {
            return CardBuilder()
        }

        fun player(): PlayerBuilder {
            return PlayerBuilder()
        }

        fun column(): ColumnBuilder {
            return ColumnBuilder()
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

class ColumnBuilder {

    private val column = Column()

    fun withCard(card: Card): ColumnBuilder {
        column.add(card)
        return this
    }

    fun please(): Column {
        return column
    }
}