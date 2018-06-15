package com.twopixeled.blinker.assets.assetTypes

/**
 * These types of assets listen to player's touch on the screen
 */
interface Touchable {
    fun touchDown(screenX: Float, screenY: Float)
}