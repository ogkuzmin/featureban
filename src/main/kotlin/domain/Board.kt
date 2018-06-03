package domain

open class Board private constructor() {

    companion object {

        fun init(todoCapacity: Int): Board {
            val board = Board()
            board.generateCardsForTodoColumns(todoCapacity)
            return board
        }
    }

    val todoColumn = Column()
    val inProgressColumn = Column()
    val verificationColumn = Column()
    val doneColumn = Column()
    private val players: MutableList<Player> = ArrayList()

    private fun generateCardsForTodoColumns(cardsCount: Int) {
        val cards = Array<Card>(cardsCount, { index: Int -> Card() } )
        cards.forEach { card -> todoColumn.add(card) }
    }

    open fun moveToProgress(player: Player): Card? {
        val card = todoColumn.cards().firstOrNull()

        if (card != null) {
            card.owner = player
            inProgressColumn.add(card)
            todoColumn.remove(card)
        }

        return card
    }

    open fun moveToVerification(card: Card) {
        verificationColumn.add(card)
        inProgressColumn.remove(card)
    }

    open fun moveToDone(card: Card) {
        doneColumn.add(card)
        verificationColumn.remove(card)
    }

    open fun addPlayer(player: Player) {
        players.add(player)
    }

    open fun players(): List<Player> {
        return players
    }

    open fun setWipLimit(wipLimit: Int) {
        inProgressColumn.setWip(wipLimit)
        verificationColumn.setWip(wipLimit)
    }
}