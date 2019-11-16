package github.jdrost1818.turtle.game

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.Objects.isNull
import java.util.stream.Collectors

@Component
class GameRepository {

    private val data = mutableListOf(
            Game(
                    UUID.fromString("90fab123-ae2b-42fb-97d8-a321b223c901"),
                    UUID.fromString("4d4c30d8-ae2b-42fb-97d8-a24cb994671e"),
                    UUID.fromString("eee42914-3c6b-432c-821f-525e86052a7b"),
                    mutableListOf(
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(1)),
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(2)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(3)),
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(4)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(5)),
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(6)),
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(7))
                    ),
                    ended = LocalDateTime.now().plusSeconds(25)),
            Game(
                    UUID.fromString("a9012b3e-ae2b-42fb-97d8-d20aceb27620"),
                    UUID.fromString("4d4c30d8-ae2b-42fb-97d8-a24cb994671e"),
                    UUID.fromString("eee42914-3c6b-432c-821f-525e86052a7b"),
                    mutableListOf(
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(1)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(2)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(3)),
                            GameEvent(player = GameParticipant.PLAYER_1, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(4)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(5)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(6)),
                            GameEvent(player = GameParticipant.PLAYER_2, action = GameAction.SCORED, timestamp = LocalDateTime.now().plusSeconds(7))

                    ),
                    ended = LocalDateTime.now().plusSeconds(25)))

    fun find(): Page<Game> = PageImpl(data)

    fun findById(id: UUID): Game = data.stream()
            .filter { player -> player.id == id }
            .findFirst()
            .orElse(null)

    fun save(game: Game): Game {
        data.removeIf { curGame -> curGame.id == game.id }
        data.add(game)

        return game
    }

    fun findActive(): List<Game> = data.stream()
            .filter { game -> isNull(game.ended) }
            .collect(Collectors.toList())
}