package github.jdrost1818.turtle.player

import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/players")
internal class PlayerController(private val service: PlayerService) {

    @GetMapping
    fun getPlayers(): Page<Player> = service.search()

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: UUID): Player = service.get(id)

}