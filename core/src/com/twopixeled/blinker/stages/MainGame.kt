package com.twopixeled.blinker.stages

import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.backgrounds.FuturisticBackground
import com.twopixeled.blinker.assets.characters.Runner
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The actual main game
 */
class MainGame : Stage() {
    override val assets: List<Asset> = listOf(
            FuturisticBackground(),
            Runner(),
            Teleport()
    )
}