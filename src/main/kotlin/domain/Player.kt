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
        if (board.inProgressColumn.containsCardOf(this)){
            if (!board.verificationColumn.isLimitSpent()) {
                val card = board.inProgressColumn.cards().first { card -> card.isOwnedBy(this) }
                board.moveToVerification(card)
            } else {
                val card = board.inProgressColumn.cards().first { it.isOwnedBy(this) && it.isBlocked }
                card.isBlocked = false
            }
        }
    }
}