package com.twopixeled.blinker.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.twopixeled.blinker.assets.assetTypes.Asset
import com.twopixeled.blinker.assets.assetTypes.Draggable
import com.twopixeled.blinker.assets.assetTypes.Touchable
import com.twopixeled.blinker.config.Config

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

    private var world: World? = null
    private var camera: OrthographicCamera? = null
    private var debugRenderer: Box2DDebugRenderer? = null

    /**
     * Draws all the assets for this stage
     */
    fun draw(batch: SpriteBatch) {
        assets.forEach { asset: Asset -> asset.draw(batch) }

        if (world is World) {
            for (c in 1..4) world?.step(1 / 60f, 6, 2)
            debugRenderer?.render(world, camera!!.combined)
        }
    }

    /**
     * Disposes all assets for this stage
     */
    fun dispose() {
        assets.forEach { asset: Asset -> asset.dispose() }
        world?.dispose()
    }

    /**
     * Acquire all draggable assets from this stage
     */
    fun getDraggables(): List<Draggable> {
        return assets.filterIsInstance(Draggable::class.java)
    }

    /**
     * Acquire all touchable assets from this stage
     */
    fun getTouchables(): List<Touchable> {
        return assets.filterIsInstance(Touchable::class.java)
    }

    /**
     * Creates a world of physics to apply for this stage. Use this if you want physics
     * engine applied in this stage
     */
    fun createPhysicsWorld(): World {
        if (Config.IS_DEBUG) {
            debugRenderer = Box2DDebugRenderer()
            camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            camera!!.viewportHeight = Gdx.graphics.height.toFloat()
            camera!!.viewportWidth = Gdx.graphics.width.toFloat()
            camera!!.position.set(camera!!.viewportWidth * .5f, camera!!.viewportHeight * .5f, 0f)
            camera!!.update()
        }

        world = World(Vector2(0f, -98f), true)
        return world!!
    }
}