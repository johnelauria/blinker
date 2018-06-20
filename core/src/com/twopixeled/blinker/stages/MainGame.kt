package com.twopixeled.blinker.stages

import com.twopixeled.blinker.assets.backgrounds.FuturisticBackground
import com.twopixeled.blinker.assets.characters.Runner
import com.twopixeled.blinker.assets.environment.Floor
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The actual main game
 */
class MainGame : Stage() {
    init {
        val world = createPhysicsWorld()
        val teleport = Teleport()

        assets.add(FuturisticBackground())
        assets.add(teleport)
        assets.add(Runner(world, teleport))
        assets.add(Floor(world))
    }
}