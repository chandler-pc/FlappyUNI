package com.flappy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Date;


public class FlappyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Floor floor;
	Texture bg;
	long timeJump = 0;
	long timeFall = 0;
	float jumpSpeed = 500.0f;
	float fallSpeed = 180.0f;
	boolean start = false;
	float temp = 0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(new Texture("flappy.png"));
		bg = new Texture("background.jpg");
		floor = new Floor(new Texture("floor.jpg"));
	}

	@Override
	public void render () {
		Date tempDate = new Date();
		if(start){
			if(player.getPosition().y  <= 680){
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !player.isJump){
					timeJump = tempDate.getTime();
					player.isJump = true;
				}
				if(tempDate.getTime() <= timeJump+200 && player.isJump){
					player.changeRotation(30);
					player.jump(jumpSpeed);
					timeFall = tempDate.getTime();
				}else{
					player.isJump = false;
					player.changeRotation(-30);
				}
			}
			player.jump(-fallSpeed);
			floor.changePosition();
			if(player.getRectangle().overlaps(floor.getRectangle())){
				start = false;
			}
		}else{
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
				start = true;
			}
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(bg,0,0,480,720);
		floor.draw(batch);
		player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
		floor.dispose();
		player.dispose();
	}
}
