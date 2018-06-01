package domain

class Column {

    private val cards: MutableList<Card> = ArrayList()

    fun add(card: Card) {
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

    fun cards(): List<Card> {
        return cards
    }

    fun remove(card: Card) {
        cards.remove(card)
    }
}