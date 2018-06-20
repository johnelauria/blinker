package com.twopixeled.blinker.assets.environment

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.twopixeled.blinker.assets.assetTypes.Asset

class Floor(private val world: World) : Asset {

    init {
        createFloor()
    }

    override fun draw(batch: SpriteBatch) {
        // Floor assets to be added here later...
    }

    override fun dispose() {
        // Floor assets to be added here later...
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
}