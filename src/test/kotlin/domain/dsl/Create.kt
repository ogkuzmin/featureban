package domain.dsl

import domain.Board
import domain.Card
import domain.Column
import domain.Player

class Create {

    companion object {

        fun card() = CardBuilder()
        fun player() = PlayerBuilder()
        fun column() = ColumnBuilder()
        fun board() = BoardBuilder()
    }
}

class CardBuilder {

    private val card = Card()

    fun withOwner(player: Player): CardBuilder {
        card.owner = player
        return this
    }

    fun please() = card
}

class PlayerBuilder {

    private val player = Player()

    fun please() = player
}

class ColumnBuilder {

    private val column = Column()

    fun withCard(card: Card): ColumnBuilder {
        column.add(card)
        return this
    }

    fun please() = column
}

class BoardBuilder {

    lateinit var board: Board

    fun withTodoCapacity(capacity: Int): BoardBuilder {
        board = Board.init(capacity)
        return this
    }

    fun please() = board
}