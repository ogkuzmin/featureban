package domain

import java.util.*

enum class CoinSides {
    HEADS, TAILS
}

open class Coin {

    private val random = Random()

    open fun flip(): CoinSides {
        return if (random.nextBoolean()) CoinSides.HEADS else CoinSides.TAILS
    }
}