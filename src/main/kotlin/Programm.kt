import domain.statistic.StatisticFormatter
import domain.statistic.StatisticsCollector

fun main(args: Array<String>) {
    val program = Program()

    program.investigateWipLimitDependency(1000, 3, 15, 9)
    program.investigateWipLimitDependency(1000, 4, 15, 9)

}

class Program {

    private val statisticsCollector = StatisticsCollector()
    private val statisticsFormatter = StatisticFormatter()

    fun investigateWipLimitDependency(gamesCount: Int, playersCount: Int, roundsCount: Int, maxWip: Int) {
        for (i in 1..maxWip) {
            printResultOf(gamesCount, playersCount, roundsCount, i)
        }
    }

    private fun printResultOf(gamesCount: Int, playersCount: Int, roundsCount: Int, wipLimit: Int) {
        println(statisticsFormatter.format(statisticsCollector.collectStatistic(
                gamesCount,
                playersCount,
                roundsCount,
                wipLimit)))
    }
}