package github.jdrost1818.turtle.player

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerService(private val repository: PlayerRepository) {

    fun search(): Page<Player> = repository.find()

    fun get(id: UUID): Player = repository.findById(id);

}