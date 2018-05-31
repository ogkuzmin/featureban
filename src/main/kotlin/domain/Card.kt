package domain

class Card {

    var owner: Player? = null
    var isBlocked: Boolean = false

    fun isOwnedBy(player: Player): Boolean {
        return owner == player
    }
}