package domain

class Column {

    companion object {
        const val UNDEFINED_WIP_LIMIT = -1
    }

    private var limitWip: Int = UNDEFINED_WIP_LIMIT

    private val cards: MutableList<Card> = ArrayList()

    fun add(card: Card) {
        if (!isLimitSpent()) {
            cards.add(card)
        } else {
            throw IllegalStateException("WIP limit is spent!")
        }
    }

    fun setWip(limitWip: Int) {
        this.limitWip = limitWip
    }

    fun isLimitSpent() = cards.size == limitWip

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

    fun isEmpty(): Boolean {
        return cards.size == 0
    }
}