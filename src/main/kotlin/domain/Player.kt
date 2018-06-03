package domain

class Player {

    fun play(coin: Coin): Boolean {
        return CoinSide.TAILS == coin.flip()
    }

    fun playOn(board: Board, coin: Coin) {
        if (play(coin)) {
            playWhenWin(board)
        }
    }

    private fun playWhenWin(board: Board) {
        if (board.verificationColumn.containsCardOf(this)) {
            val card = board.verificationColumn.getNonBlockedCardOf(this)
            if (card != null) {
                board.moveToDone(card)
            } else {
                val blockedCard = board.verificationColumn.getBlockedCardOf(this)
                blockedCard?.isBlocked = false
            }
        } else if (board.inProgressColumn.containsCardOf(this)) {
            val card = board.inProgressColumn.getNonBlockedCardOf(this)
            if (card != null) {
                board.moveToVerification(card)
            } else {
                val blockedCard = board.inProgressColumn.getBlockedCardOf(this)
                blockedCard?.isBlocked = false
            }
        } else {
            if (!board.inProgressColumn.isLimitSpent()) {
                board.moveToProgress(this)
            } else {
                helpOtherPlayerOn(board)
            }
        }
    }

    private fun helpOtherPlayerOn(board: Board) {
        if (!board.verificationColumn.isEmpty()) {
            val nonBlockedCard = board.verificationColumn.cards().firstOrNull { card -> !card.isBlocked }
            if (nonBlockedCard != null) {
                board.moveToDone(nonBlockedCard)
            } else {
                val blockedCard = board.verificationColumn.cards().first{ card -> card.isBlocked }
                blockedCard.isBlocked = false
            }
        } else if (!board.inProgressColumn.isEmpty()) {
            val cardInProgressNonBlocked = board.inProgressColumn.cards().firstOrNull { card -> !card.isBlocked }
            if (cardInProgressNonBlocked != null) {
                board.moveToVerification(cardInProgressNonBlocked)
            } else {
                val cardInProgressBlocked = board.inProgressColumn.cards().first { card -> card.isBlocked }
                cardInProgressBlocked.isBlocked = false
            }
        }
    }

    private fun Column.getNonBlockedCardOf(player: Player): Card? {
        return this.cards().firstOrNull { card ->
            card.isOwnedBy(player) && !card.isBlocked
        }
    }

    private fun Column.getBlockedCardOf(player: Player): Card? {
        return this.cards().firstOrNull { card ->
            card.isOwnedBy(player) && card.isBlocked
        }
    }
}