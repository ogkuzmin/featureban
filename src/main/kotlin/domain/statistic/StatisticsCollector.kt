package domain.statistic

import domain.core.Coin
import domain.core.Game
import domain.core.Player

class StatisticsCollector {

    private val todoCapacity = 10000

    fun collectStatistic(gamesCount: Int, playersCount: Int, roundsCount: Int, wipLimit: Int): StatisticInfo {
        var summOfDoneCards = 0

        val players = Array(playersCount, { Player() })
        val coin = Coin()

        for (i in 1..gamesCount) {
            val game = Game(roundsCount, todoCapacity, wipLimit, coin).also { game ->
                players.forEach { player ->
                    game.join(player) }
            }
            game.play()
            summOfDoneCards += game.getDoneCount()
        }

        return StatisticInfo(summOfDoneCards / gamesCount.toFloat(), gamesCount, playersCount, roundsCount, wipLimit)
    }
}