package com.twopixeled.blinker.stages

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.assetTypes.Draggable
import com.twopixeled.blinker.assets.assetTypes.Touchable

/**
 * Placeholder to group all assets and have them displayed together. E.g. main menu stage will
 * show buttons, whilst game stage will show characters, backgrounds, obstacles, etc.
 */
abstract class Stage {
    /**
     * List of all assets for this stage. Mind the order of assets. Assets on latter indices
     * will show above the assets on lower indices
     */
    protected var assets = mutableListOf<Asset>()

    /**
     * Draws all the assets for this stage
     */
    fun draw(batch: SpriteBatch) {
        assets.forEach { asset: Asset -> asset.draw(batch) }
    }

    /**
     * Disposes all assets for this stage
     */
    fun dispose() {
        assets.forEach { asset: Asset -> asset.dispose() }
    }

    /**
     * Acquire all draggable assets from this stage
     */
    fun getDraggables(): List<Draggable> {
        return assets.filterIsInstance(Draggable::class.java)
    }

    fun getTouchables(): List<Touchable> {
        return assets.filterIsInstance(Touchable::class.java)
    }
}