package domain.statistic

class StatisticFormatter {

    fun format(info: StatisticInfo): String {
        return "Got data: for ${info.gamesCount} games with ${info.playersCount} players, WIP limit is ${info.wipLimit}" +
                ", ${info.roundsCount} rounds average rate is ${info.rate}"
    }
}