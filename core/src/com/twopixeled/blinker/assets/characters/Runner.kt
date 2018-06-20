package com.twopixeled.blinker.assets.characters

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.assetTypes.Touchable
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The main character of the game. Will keep on running and teleport when player taps on the screen
 */
class Runner(world: World, private val teleport: Teleport) : Asset, Touchable {
    private var runnerAnimation: Animation<TextureRegion>
    private var runnerAtlas: TextureAtlas = TextureAtlas(Gdx.files.internal("runner/runner.atlas"))
    private var animationTime = 0f
    private var runnerX = Gdx.graphics.width / 12f
    private var runnerY = 0f
    private var teleporting = false
    private var teleportTimer = 15
    private var runnerBody: Body

    init {
        val runnerBodyDef = BodyDef()
        val runnerShape = PolygonShape()
        val runnerFixtureDef = FixtureDef()

        runnerAnimation = Animation(0.1f, runnerAtlas.regions)
        runnerBodyDef.type = BodyDef.BodyType.DynamicBody
        runnerBodyDef.position.set(runnerX, runnerY)

        runnerBody = world.createBody(runnerBodyDef)
        runnerShape.setAsBox(runnerWidth(runnerAnimation.getKeyFrame(0f)) / 2, runnerHeight() / 2)

        runnerFixtureDef.shape = runnerShape
        runnerFixtureDef.density = 1f
        runnerFixtureDef.restitution = 0.5f

        runnerBody.createFixture(runnerFixtureDef)
        runnerShape.dispose()
    }

    override fun dispose() {
        runnerAtlas.dispose()
    }

    override fun draw(batch: SpriteBatch) {
        animationTime += Gdx.graphics.deltaTime * 1.5f
        val runnerRegion = runnerAnimation.getKeyFrame(animationTime, true)

        runnerX = runnerBody.position.x - runnerRegion.regionWidth * 2
        runnerY = runnerBody.position.y - runnerRegion.regionHeight * 2

        if (teleporting) {
            teleportTimer()
        } else {
            batch.draw(runnerRegion, runnerX, runnerY, runnerWidth(runnerRegion), runnerHeight())
        }
    }

    override fun touchDown(screenX: Float, screenY: Float) {
        if (screenX > Gdx.graphics.width / 1.5f) {
            runnerBody.setTransform(teleport.teleportX, teleport.teleportY, 0f)
            runnerBody.setLinearVelocity(-teleport.teleportX, Gdx.graphics.height.toFloat())
            teleporting = true
        }
    }

    /**
     * Runner width is 10% of screen width
     */
    private fun runnerWidth(runnerRegion: TextureRegion): Float {
        return Gdx.graphics.width / 10f + (runnerRegion.regionWidth / 1.75f)
    }

    /**
     * Runner height is 5% of screen height
     */
    private fun runnerHeight(): Float {
        return Gdx.graphics.height / 5f
    }

    /**
     * Give a 15millisecond of disappearance of character when teleporting
     */
    private fun teleportTimer() {
        teleportTimer -= 1

        if (teleportTimer <= 0) {
            teleporting = false
            teleportTimer = 15
        }
    }
}