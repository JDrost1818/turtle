package github.jdrost1818.turtle.player

import java.util.*

data class Player(
        val id: UUID,
        val name: String,
        val wins: Int,
        val losses: Int,
        val rank: Int)