package com.twopixeled.blinker

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.twopixeled.blinker.assets.assetTypes.Draggable

/**
 * Listens for all inputs on how the user interacts with the game, then passes these information
 * to game objects for them to do their jobs
 */
class InputListener : InputProcessor {
    var draggables = listOf<Draggable>()

    init {
        val im = InputMultiplexer()

        im.addProcessor(this)
        Gdx.input.inputProcessor = im
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
        draggables.forEach {
            draggables : Draggable -> draggables.moveOrDrag(screenX.toFloat(), normalisedY(screenY))
        }
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        draggables.forEach {
            draggables : Draggable -> draggables.moveOrDrag(screenX.toFloat(), normalisedY(screenY))
        }
        return false
    }

    /**
     * Calculation for the correct y position when players click / drag across the screen
     */
    private fun normalisedY(screenY: Int): Float {
        return Gdx.graphics.height - screenY.toFloat()
    }
}