package domain

class Game {

    private lateinit var board: Board
    private val players: MutableList<Player> = ArrayList()
    private var countOfRounds: Int = 1
    private lateinit var coin: Coin

    private constructor()

    constructor(countOfRounds: Int, todoCapacity: Int, wipLimit: Int = Column.UNDEFINED_WIP_LIMIT, coin: Coin) {
        this.countOfRounds = countOfRounds
        board = Board.init(todoCapacity)
        board.setWipLimit(wipLimit)
        this.coin = coin
    }

    fun join(player: Player) {
        players.add(player)
    }

    fun play() {
        for (i in 1..countOfRounds) {
            players.forEach { player ->
                player.playOn(board, coin)
            }
        }
    }
}