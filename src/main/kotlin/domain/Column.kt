package domain

class Column {

    private val cards: MutableList<Card> = ArrayList()

    fun set(card: Card) {
        cards.add(card)
    }

    fun containsCardOf(player: Player): Boolean {
        cards.forEach { card ->
            if (card.isOwnedBy(player)) {
                return true
            }
        }

        return false
    }
}