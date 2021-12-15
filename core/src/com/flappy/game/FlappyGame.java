package com.flappy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Date;
import java.util.Random;


public class FlappyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Floor floor;
	Texture bg;
	long timeJump = 0;
	long timeFall = 0;
	float jumpSpeed = 500.0f;
	float fallSpeed = 200.0f;
	boolean start = false;
	float temp = 0;
	Pipe arrPipes[];
	boolean gameOver = false;
	int score = 0;
	BitmapFont font;
	GlyphLayout fontWidth;
	@Override
	public void create () {
		arrPipes = new Pipe[3];
		batch = new SpriteBatch();
		player = new Player(new Texture("flappy.png"));
		bg = new Texture("background.jpg");
		floor = new Floor(new Texture("floor.jpg"));
		for(int i = 0;i<3;i++){
			arrPipes[i] = new Pipe(new Texture("pipeUp.png"),new Texture("pipeDown.png"), 550 + (300*i),Math.floor(Math.random()*(0-150+1)+150));
		}
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.getData().setScale(2.5f);
		fontWidth = new GlyphLayout(font,Integer.toString(score));
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
				gameOver = true;
			}
			for(int i = 0;i<3;i++){
				arrPipes[i].move();
				if(player.getRectangle().overlaps(arrPipes[i].getRectangleUp()) || player.getRectangle().overlaps(arrPipes[i].getRectangleDown())){
					start = false;
					gameOver = true;
				}
				if(player.getRectangle().overlaps(arrPipes[i].getRectangleMid())) {
					if(arrPipes[i].getState()){
						score++;
						System.out.println(score);
						arrPipes[i].setState(false);
					}
				}

			}
		}else{
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !gameOver){
				start = true;
			}
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(bg,0,0,480,720);
		for(int i = 0;i<3;i++){
			arrPipes[i].draw(batch);
		}
		floor.draw(batch);
		player.draw(batch);
		fontWidth.setText(font,Integer.toString(score));
		font.draw(batch,Integer.toString(score),240 - fontWidth.width/2,650);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bg.dispose();
		for(int i = 0;i<3;i++){
			arrPipes[i].dispose();
		}
		floor.dispose();
		player.dispose();
		font.dispose();
	}
}
