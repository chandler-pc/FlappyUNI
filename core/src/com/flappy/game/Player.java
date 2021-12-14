package com.flappy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.css.Rect;

public class Player {
    private Texture texture;
    private float x = 206.0f;
    private float y = 400.0f;
    private float rotation = 0;
    private Sprite sprite;
    Rectangle rectangle;
    boolean isJump = false;

    public Player(Texture texture){
        this.texture = texture;
        sprite = new Sprite(this.texture);
        sprite.setSize(68,48);
        sprite.setOrigin(34,24);
        rectangle = new Rectangle();
    }
    public void draw(SpriteBatch batch){
        rectangle.set((int)x,(int)y,68,48);
        sprite.setPosition((int)x,(int)y);
        sprite.setRotation(rotation);
        sprite.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }

    public void jump(float jumpSpeed){
        y += (Gdx.graphics.getDeltaTime() * jumpSpeed);
    }

    public void changeRotation(float rotation){
        this.rotation = rotation;
    }

    public float getRotation(){
        return rotation;
    }

    public Vector2 getPosition(){
        return new Vector2(x,y);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
