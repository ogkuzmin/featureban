package domain.core

import java.util.*

enum class CoinSide {
    HEADS, TAILS
}

open class Coin {

    private val random = Random()

    open fun flip(): CoinSide {
        return if (random.nextBoolean()) CoinSide.HEADS else CoinSide.TAILS
    }
}