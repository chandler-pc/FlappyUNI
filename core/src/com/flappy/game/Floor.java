package com.flappy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Floor {
    private Texture texture;
    private Sprite floor1;
    private Sprite floor2;
    float x1 = 0, x2 = 480;
    float floorSpeed = 200.0f;
    private Rectangle rectangle;
    public Floor(Texture texture){
        this.texture = texture;
        floor1 = new Sprite(this.texture);
        floor2 = new Sprite(this.texture);
        floor1.setSize(480,160);
        floor2.setSize(480,160);
        rectangle = new Rectangle();
        rectangle.set(0,0,480,150);
    }
    public void draw(SpriteBatch batch){
        floor1.setPosition((int)x1,0);
        floor2.setPosition((int)x2,0);
        floor1.draw(batch);
        floor2.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }
    public void changePosition(){
        x1 -= Gdx.graphics.getDeltaTime() * floorSpeed;
        x2 -= Gdx.graphics.getDeltaTime() * floorSpeed;
        if(x1<=-480){
            x1 = 480;
        }
        if(x2<=-480){
            x2 = 480;
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
