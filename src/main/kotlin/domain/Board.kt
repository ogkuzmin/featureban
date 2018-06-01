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
}