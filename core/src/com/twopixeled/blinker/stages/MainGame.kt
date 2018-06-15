package com.twopixeled.blinker.stages

import com.twopixeled.blinker.assets.backgrounds.FuturisticBackground
import com.twopixeled.blinker.assets.characters.Runner
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The actual main game
 */
class MainGame : Stage() {

    init {
        val teleport = Teleport()
        val runner = Runner()
        runner.teleport = teleport

        assets.add(FuturisticBackground())
        assets.add(teleport)
        assets.add(runner)
    }
}