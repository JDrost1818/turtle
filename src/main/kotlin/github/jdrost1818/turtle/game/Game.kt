package github.jdrost1818.turtle.game

import java.time.LocalDateTime
import java.util.*

data class Game(
        val id: UUID,
        val playerOne: UUID,
        val playerTwo: UUID,
        val events: MutableList<GameEvent>,
        val started: LocalDateTime = LocalDateTime.now(),
        var ended: LocalDateTime? = null
)