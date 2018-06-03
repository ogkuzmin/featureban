package domain

class Player {

    fun play(coin: Coin): Boolean {
        return CoinSide.TAILS == coin.flip()
    }

    fun playOn(board: Board, coin: Coin) {
        if (play(coin)) {
            playWhenWin(board)
        } else {
            playWhenLose(board)
        }
    }

    private fun playWhenWin(board: Board) {
        if (board.verificationColumn.containsCardOf(this)) {
            playWinIn(board.verificationColumn, { card -> board.moveToDone(card) }, { card -> card.isBlocked = false })
        } else if (board.inProgressColumn.containsCardOf(this)) {
            playWinIn(board.inProgressColumn, { card -> board.moveToVerification(card) }, { card -> card.isBlocked = false })
        } else {
            if (!board.inProgressColumn.isLimitSpent()) {
                board.moveToProgress(this)
            } else {
                helpOtherPlayerOn(board)
            }
        }
    }

    private fun playWhenLose(board: Board) {
        val nonBlockedCardFormVerificationColumn = board.verificationColumn.getNonBlockedCardOf(this)
        if (nonBlockedCardFormVerificationColumn != null) {
            nonBlockedCardFormVerificationColumn.isBlocked = true
        } else {
            val nonBlockedCardFromInProgressColumn = board.inProgressColumn.getNonBlockedCardOf(this)
            nonBlockedCardFromInProgressColumn?.isBlocked = true
        }

    }

    private fun playWinIn(column: Column, forNonBlockedAction: (Card) -> Unit, forBlockedAction: (Card) -> Unit) {
        val card = column.getNonBlockedCardOf(this)
        if (card != null) {
            forNonBlockedAction(card)
        } else {
            val blockedCard = column.getBlockedCardOf(this)
            forBlockedAction(blockedCard!!)
        }
    }

    private fun helpOtherPlayerOn(board: Board) {
        if (!board.verificationColumn.isEmpty()) {
            helpOtherPlayerIn(board.verificationColumn, { card -> board.moveToDone(card) }, { card -> card.isBlocked = false })
        } else if (!board.inProgressColumn.isEmpty()) {
            helpOtherPlayerIn(board.inProgressColumn, { card -> board.moveToVerification(card) }, { card -> card.isBlocked = false })
        }
    }

    private fun helpOtherPlayerIn(column: Column, forNonBlockedAction: (Card) -> Unit, forBlockedAction: (Card) -> Unit) {
        val nonBlockedCard = column.cards().firstOrNull { card -> !card.isBlocked }
        if (nonBlockedCard != null) {
            forNonBlockedAction(nonBlockedCard)
        } else {
            val blockedCard = column.cards().first { card -> card.isBlocked }
            forBlockedAction(blockedCard)
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