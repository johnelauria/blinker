package com.twopixeled.blinker.assets.characters

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.assetTypes.Touchable
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The main character of the game. Will keep on running and teleport when player taps on the screen
 */
class Runner  : Asset, Touchable {
    lateinit var teleport: Teleport

    private var runnerAnimation: Animation<TextureRegion>
    private var runnerAtlas: TextureAtlas = TextureAtlas(Gdx.files.internal("runner/runner.atlas"))
    private var animationTime = 0f
    private var runnerX = 0f
    private var runnerY = 0f

    init {
        runnerAnimation = Animation(0.1f, runnerAtlas.regions)
    }

    override fun dispose() {
        runnerAtlas.dispose()
    }

    override fun draw(batch: SpriteBatch) {
        animationTime += Gdx.graphics.deltaTime * 1.5f
        val runnerRegion = runnerAnimation.getKeyFrame(animationTime, true)

        batch.draw(runnerRegion, runnerX, runnerY, runnerWidth(runnerRegion), runnerHeight())
        fallByGravity()
    }

    override fun touchDown(screenX: Float, screenY: Float) {
        if (screenX > Gdx.graphics.width / 1.5f) {
            runnerY = teleport.teleportY
        }
    }

    private fun runnerWidth(runnerRegion: TextureRegion): Float {
        return Gdx.graphics.width / 10f + (runnerRegion.regionWidth / 1.75f)
    }

    private fun runnerHeight(): Float {
        return Gdx.graphics.height / 5f
    }

    private fun fallByGravity() {
        if (runnerY > 0) {
            runnerY -= Gdx.graphics.height / 200f
        } else {
            runnerY = 0f
        }
    }
}