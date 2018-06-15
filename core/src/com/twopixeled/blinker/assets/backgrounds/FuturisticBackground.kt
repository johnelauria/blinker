package com.twopixeled.blinker.assets.backgrounds

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.twopixeled.blinker.assets.assetTypes.Asset

/**
 * Background that keeps on looping from behind. This is to give an illusion that the character
 * is running
 */
class FuturisticBackground : Asset {
    private var background = Texture(Gdx.files.internal("backgrounds/futuristic_background.jpg"))
    private var bgAnimation = 0f
    private var bgSprite: Sprite
    private var bgSprite2: Sprite
    private val bgWidthMultiplier = 3f

    init {
        val bgWidth = Gdx.graphics.width * bgWidthMultiplier
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat)
        bgSprite = Sprite(background)
        bgSprite.setSize(bgWidth, Gdx.graphics.height.toFloat())
        bgSprite.setPosition(0f, 0f)

        bgSprite2 = Sprite(background)
        bgSprite2.setSize(bgWidth, Gdx.graphics.height.toFloat())
        bgSprite2.setPosition(bgWidth, 0f)
    }

    override fun dispose() {
        background.dispose()
    }

    override fun draw(batch: SpriteBatch) {
        bgAnimation = Gdx.graphics.deltaTime * 700
        bgSprite.draw(batch)
        bgSprite2.draw(batch)

        bgSprite.setPosition(bgSprite.x - bgAnimation, 0f)
        bgSprite2.setPosition(bgSprite2.x - bgAnimation, 0f)

        if (bgSprite.x + bgSprite.width <= 0) {
            bgSprite.setPosition(bgSprite2.x + bgSprite2.width, 0f)
        }

        if (bgSprite2.x + bgSprite2.width <= 0) {
            bgSprite2.setPosition(bgSprite.x + bgSprite.width, 0f)
        }
//        batch.draw(background, 0f, 0f, bgAnimation, 0, Gdx.graphics.width, Gdx.graphics.height)
    }
}