package domain

class Player {

    fun play(coin: Coin): Boolean {
        return CoinSides.TAILS == coin.flip()
    }
}