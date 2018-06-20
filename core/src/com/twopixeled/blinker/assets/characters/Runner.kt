package com.twopixeled.blinker.assets.characters

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.assetTypes.Touchable
import com.twopixeled.blinker.assets.icons.Teleport

/**
 * The main character of the game. Will keep on running and teleport when player taps on the screen
 */
class Runner  : Asset, Touchable {
    private val showDebug = false
    lateinit var teleport: Teleport
    private lateinit var runnerAnimation: Animation<TextureRegion>
    private var runnerAtlas: TextureAtlas = TextureAtlas(Gdx.files.internal("runner/runner.atlas"))
    private var animationTime = 0f
    private var runnerX = Gdx.graphics.width / 12f
    private var runnerY = 0f
    private var teleporting = false
    private var teleportTimer = 15
    private lateinit var runnerBody: Body
    private var world = World(Vector2(0f, -98f), true)
    private var camera: OrthographicCamera? = null
    private var debugRenderer: Box2DDebugRenderer? = null

    init {
        createCamera()
        createRunner()
        createFloor()
    }

    override fun dispose() {
        runnerAtlas.dispose()
        world.dispose()
    }

    override fun draw(batch: SpriteBatch) {
        animationTime += Gdx.graphics.deltaTime * 1.5f
        val runnerRegion = runnerAnimation.getKeyFrame(animationTime, true)

        runnerX = runnerBody.position.x - runnerRegion.regionWidth * 2
        runnerY = runnerBody.position.y - runnerRegion.regionHeight * 2

        if (teleporting) {
            teleportTime()
        } else {
            batch.draw(runnerRegion, runnerX, runnerY, runnerWidth(runnerRegion), runnerHeight())
        }

        for (c in 1..4) stepWorld()
        debugRenderer?.render(world, camera!!.combined)
    }

    override fun touchDown(screenX: Float, screenY: Float) {
        if (screenX > Gdx.graphics.width / 1.5f) {
            runnerBody.setTransform(teleport.teleportX, teleport.teleportY, 0f)
            runnerBody.setLinearVelocity(-teleport.teleportX, Gdx.graphics.height.toFloat())
            teleporting = true
        }
    }

    private fun runnerWidth(runnerRegion: TextureRegion): Float {
        return Gdx.graphics.width / 10f + (runnerRegion.regionWidth / 1.75f)
    }

    private fun runnerHeight(): Float {
        return Gdx.graphics.height / 5f
    }

    private fun teleportTime() {
        teleportTimer -= 1

        if (teleportTimer <= 0) {
            teleporting = false
            teleportTimer = 15
        }
    }

    private fun stepWorld() {
        world.step(1/60f, 6, 2)
    }

    private fun createCamera() {
        if (showDebug) {
            debugRenderer = Box2DDebugRenderer()
            camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            camera!!.viewportHeight = Gdx.graphics.height.toFloat()
            camera!!.viewportWidth = Gdx.graphics.width.toFloat()
            camera!!.position.set(camera!!.viewportWidth * .5f, camera!!.viewportHeight * .5f, 0f)
            camera!!.update()
        }
    }

    private fun createFloor() {
        val floorShape = PolygonShape()
        val floorDef = BodyDef()
        val floorFixtureDef = FixtureDef()

        floorDef.type = BodyDef.BodyType.StaticBody

        val floorBody = world.createBody(floorDef)
        floorShape.setAsBox(Gdx.graphics.width.toFloat() / 2, 0f)

        floorFixtureDef.shape = floorShape
        floorFixtureDef.density = 0f

        floorBody.createFixture(floorFixtureDef)
        floorBody.setTransform(0f, 0f, 0f)

        floorShape.dispose()
    }

    private fun createRunner() {
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
}