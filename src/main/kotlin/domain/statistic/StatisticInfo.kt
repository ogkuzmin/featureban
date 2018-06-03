package domain.statistic

data class StatisticInfo(
        val rate: Float,
        val gamesCount: Int,
        val playersCount: Int,
        val roundsCount: Int,
        val wipLimit: Int)