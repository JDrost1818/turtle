package github.jdrost1818.turtle.game

import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/games")
internal class GameController(private val service: GameService) {

    @GetMapping
    fun getGames(): Page<Game> = service.search()

    @GetMapping("/{id}")
    fun getGame(@PathVariable id: UUID): Game = service.get(id)

    @PostMapping
    fun createGame(@RequestBody gameCreationData: GameCreationData): Game = service.create(gameCreationData)

    @PostMapping(
            "/{id}/events",
            "/current/events")
    fun addEvent(@PathVariable(required = false) id: UUID?, @RequestBody event: GameEvent) : Game? = service.addEvent(id, event)

}