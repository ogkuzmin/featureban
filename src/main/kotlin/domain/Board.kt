package domain

class Board private constructor() {

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

    private fun generateCardsForTodoColumns(cardsCount: Int) {
        val cards = Array<Card>(cardsCount, { index: Int -> Card() } )
        cards.forEach { card -> todoColumn.add(card) }
    }

    fun moveToProgress(): Card? {
        val card = todoColumn.cards().firstOrNull()

        if (card != null) {
            inProgressColumn.add(card)
            todoColumn.remove(card)
        }

        return card
    }

    fun moveToVerification(card: Card) {
        verificationColumn.add(card)
        inProgressColumn.remove(card)
    }

    fun moveToDone(card: Card) {
        doneColumn.add(card)
        verificationColumn.remove(card)
    }
}