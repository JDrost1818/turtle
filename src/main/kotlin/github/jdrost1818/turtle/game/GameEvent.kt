package github.jdrost1818.turtle.game

import java.time.LocalDateTime

enum class GameAction {
    SCORED, FORFEITED
}

enum class GameParticipant {
    PLAYER_1, PLAYER_2
}

data class GameEvent(
        val player: GameParticipant,
        val action: GameAction,
        var timestamp: LocalDateTime? = LocalDateTime.now()
)