package com.twopixeled.blinker

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.twopixeled.blinker.stages.MainGame
import com.twopixeled.blinker.stages.Stage

class BlinkerGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var stage: Stage

    override fun create() {
        val inputListener = InputListener()

        batch = SpriteBatch()
        stage = MainGame()
        inputListener.draggables = stage.getDraggables()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        stage.draw(batch)
        batch.end()
    }

    override fun dispose() {
        stage.dispose()
        batch.dispose()
    }
}
