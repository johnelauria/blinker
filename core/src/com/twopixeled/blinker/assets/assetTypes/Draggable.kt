package com.twopixeled.blinker.assets.assetTypes

/**
 * These are the types of assets that are draggable across the game
 */
interface Draggable {
    /**
     * Give the x and y screen drag coordinates to the sprite
     */
    fun moveOrDrag(xPos: Float, yPos: Float)
}