package domain.statistic

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class StatisticTests {

    @Test
    fun shouldReturnStatisticInfo_withValuesThatMatchGivenArgumentsAndPositiveRate_whenCollectStatistic() {
        val statisticsCollector = StatisticsCollector()
        val gamesCount = 10
        val playersCount = 3
        val roundsCount = 15
        val wipLimit = 4

        val statisticInfo = statisticsCollector.collectStatistic(gamesCount, playersCount, roundsCount, wipLimit)

        assertEquals(gamesCount, statisticInfo.gamesCount)
        assertEquals(playersCount, statisticInfo.playersCount)
        assertEquals(roundsCount, statisticInfo.roundsCount)
        assertEquals(wipLimit, statisticInfo.wipLimit)
        assertTrue(statisticInfo.rate > 0)
    }
}