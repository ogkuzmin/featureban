package domain.dsl

import com.nhaarman.mockito_kotlin.any
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class GameTests {

    @Test
    fun shouldEveryPlayerPlays_thatJoinedToThisGameSuchTimesThatMatchToRequestedCount_whenPlay() {
        val countOfRounds = 3
        val firstPlayer = Mockito.spy(Create
                .player()
                .please())
        val secondPlayer = Mockito.spy(Create
                .player()
                .please())
        val game = Create
                .game()
                .withBoardCapacity(100)
                .withCountOfRounds(countOfRounds)
                .please()
        game.join(firstPlayer)
        game.join(secondPlayer)

        game.play()

        verify(firstPlayer, times(countOfRounds)).playOn(
                any(),
                any()
        )
        verify(secondPlayer, times(countOfRounds)).playOn(
                any(),
                any()
        )
    }
}