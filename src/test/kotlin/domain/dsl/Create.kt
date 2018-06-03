package domain.dsl

import domain.core.*

class Create {

    companion object {

        fun card() = CardBuilder()
        fun player() = PlayerBuilder()
        fun column() = ColumnBuilder()
        fun board() = BoardBuilder()
        fun game() = GameBuilder()
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

    fun withCards(cards: List<Card>): ColumnBuilder {
        cards.forEach { column.add(it) }
        return this
    }

    fun withWipLimit(limit: Int): ColumnBuilder {
        column.setWip(limit)
        return this
    }

    fun please() = column
}

class BoardBuilder {

    private var capacity = 1
    private var wipLimit = Column.UNDEFINED_WIP_LIMIT

    fun withTodoCapacity(capacity: Int): BoardBuilder {
        this.capacity = capacity
        return this
    }

    fun withWipLimit(limit: Int): BoardBuilder {
        wipLimit = limit
        return this
    }

    fun please() = Board.init(capacity).also { board -> board.setWipLimit(wipLimit) }
}

class GameBuilder {

    private var countOfRounds = 1
    private var capacity = 1
    private var wipLimit = Column.UNDEFINED_WIP_LIMIT
    private var coin: Coin? = null

    fun withBoardCapacity(capacity: Int): GameBuilder {
        this.capacity = capacity
        return this
    }

    fun withWipLimit(wipLimit: Int): GameBuilder {
        this.wipLimit = wipLimit
        return this
    }

    fun withCountOfRounds(countOfRounds: Int): GameBuilder {
        this.countOfRounds = countOfRounds
        return this
    }

    fun withCoin(coin: Coin): GameBuilder {
        this.coin = coin
        return this
    }

    fun please() = Game(countOfRounds, capacity, wipLimit, coin ?: Coin())
}