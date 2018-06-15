package com.twopixeled.blinker.assets.icons

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.twopixeled.blinker.assets.Asset

class Teleport : Asset {
    private var teleportTexture = Texture(Gdx.files.internal("icons/teleport.png"))
    private var teleportSprite = Sprite(teleportTexture)

    init {
        teleportSprite.setSize(Gdx.graphics.width / 8.5f, Gdx.graphics.height / 4f)
        teleportSprite.setPosition(Gdx.graphics.width / 4f, Gdx.graphics.height / 2f)
    }

    override fun draw(batch: SpriteBatch) {
        teleportSprite.draw(batch)
    }

    override fun dispose() {
        teleportTexture.dispose()
    }

    fun moveOrDrag(xPos: Float, yPos: Float) {
        if (xPos < Gdx.graphics.width / 3) {
            teleportSprite.y = yPos - (teleportSprite.height / 3)
        }
    }
}