package com.twopixeled.blinker

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.twopixeled.blinker.assets.backgrounds.FuturisticBackground
import com.twopixeled.blinker.assets.characters.Runner
import com.twopixeled.blinker.assets.icons.Teleport
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.input.GestureDetector


class BlinkerGame : ApplicationAdapter(), InputProcessor {
    private lateinit var batch: SpriteBatch
    private lateinit var runner: Runner
    private lateinit var background: FuturisticBackground

    private lateinit var teleport: Teleport

    override fun create() {
        val im = InputMultiplexer()
        batch = SpriteBatch()
        runner = Runner()
        background = FuturisticBackground()
        teleport = Teleport()

        // Set input listeners
        im.addProcessor(this)
        Gdx.input.inputProcessor = im
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        background.draw(batch)
        runner.draw(batch)
        teleport.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        runner.dispose()
        background.dispose()
        teleport.dispose()
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val normalisedY = Gdx.graphics.height - screenY
        teleport.moveOrDrag(screenX.toFloat(), normalisedY.toFloat())
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val normalisedY = Gdx.graphics.height - screenY
        teleport.moveOrDrag(screenX.toFloat(), normalisedY.toFloat())
        return false
    }
}
