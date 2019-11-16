package github.jdrost1818.turtle.game

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.Objects.nonNull

@Service
class GameService(private val repository: GameRepository) {

    fun search(): Page<Game> = repository.find()

    fun get(id: UUID): Game = repository.findById(id);

    fun create(gameCreationData: GameCreationData): Game {
        repository.findActive().stream()
                .map { game ->
                    game.ended = LocalDateTime.now();
                    game
                }
                .forEach { game -> this.repository.save(game) }

        return repository.save(Game(
                UUID.randomUUID(),
                gameCreationData.playerOne,
                gameCreationData.playerTwo,
                mutableListOf()
        ))
    }

    fun addEvent(id: UUID?, event: GameEvent): Game? {
        event.timestamp = event.timestamp ?: LocalDateTime.now()
        val shouldBeOneGame = if (nonNull(id)) listOf(repository.findById(id!!)) else repository.findActive()

        return when {
            shouldBeOneGame.isEmpty() -> null
            shouldBeOneGame.size > 1 -> throw IllegalStateException("More than one active game found")
            else -> {
                val game = shouldBeOneGame[0]
                game.events.add(event)

                repository.save(game)
            }
        }
    }

}