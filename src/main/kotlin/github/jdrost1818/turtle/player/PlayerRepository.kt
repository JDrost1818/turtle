package github.jdrost1818.turtle.player

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component
import java.util.*

@Component
class PlayerRepository {

    private val data = listOf(
            Player(UUID.fromString("4d4c30d8-ae2b-42fb-97d8-a24cb994671e"), "Jake", 1, 0, 1000),
            Player(UUID.fromString("eee42914-3c6b-432c-821f-525e86052a7b"), "Ryan", 0, 1, 0)
    )

    fun find(): Page<Player> = PageImpl(data)

    fun findById(id: UUID): Player = data.stream()
            .filter { player -> player.id == id }
            .findFirst()
            .orElse(null);

}