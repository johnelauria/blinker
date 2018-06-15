package com.twopixeled.blinker.assets

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface Asset {
    fun draw(batch: SpriteBatch)
    fun dispose()
}