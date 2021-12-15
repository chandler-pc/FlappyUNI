package com.flappy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pipe {
    private Texture textureUp;
    private Texture textureDown;
    private Sprite spriteUp;
    private Sprite spriteDown;
    private Rectangle rectangleUp;
    private Rectangle rectangleDown;
    private Rectangle rectangleMid;
    private float x;
    private float y;
    private float vel = 200.0f;
    boolean state = true;

    public Pipe(Texture textureUp, Texture textureDown, double xo, double yo){
        this.textureDown = textureDown;
        this.textureUp = textureUp;
        spriteDown = new Sprite(textureDown);
        spriteUp = new Sprite(textureUp);
        spriteUp.scale(2);
        spriteDown.scale(2);
        rectangleUp = new Rectangle();
        rectangleDown = new Rectangle();
        rectangleMid = new Rectangle();
        x = (float) xo;
        y = (float) yo;
        spriteUp.setPosition((float) xo,(float) yo+700);
        spriteDown.setPosition((float) xo,(float) yo);
        rectangleUp.set((float) xo,(float)yo+550,52,320);
        rectangleDown.set((float)xo,(float)yo,52,320);
        rectangleMid.set((float) xo, (float) yo,50,100);
    }

    public void draw(SpriteBatch batch){
        spriteUp.setPosition((int)x,y+700);
        spriteDown.setPosition((int)x,y);
        rectangleUp.setPosition((int)x,y+550);
        rectangleDown.setPosition((int)x,y);
        rectangleMid.setPosition((int)x,y+320);
        spriteUp.draw(batch);
        spriteDown.draw(batch);
    }

    public void move(){
        x -= Gdx.graphics.getDeltaTime() * vel;
        if(x<=-100){
            x = 850.0f;
            y = (float) Math.floor(Math.random()*(0-150+1)+150);
            setState(true);
        }
    }

    public void dispose(){
        textureUp.dispose();
        textureDown.dispose();
    }
    public Rectangle getRectangleUp(){
        return rectangleUp;
    }
    public Rectangle getRectangleDown(){
        return rectangleDown;
    }

    public Rectangle getRectangleMid() {
        return rectangleMid;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean st){
        state = st;
    }
}
