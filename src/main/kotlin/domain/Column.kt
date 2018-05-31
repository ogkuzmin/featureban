package domain

class Column {

    private val cards: MutableList<Card> = ArrayList()

    fun set(card: Card) {
    }

    fun containsCardOf(player: Player): Boolean {
        return true
    }
}