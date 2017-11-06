package com.eps.sportsim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DungeonScreen implements Screen, InputProcessor{

	
	ArrayList<DungeonRoom> DungeonLayout;
	Stage stage;
	Skin skin;
	Table DungeonContainer;
	Viewport viewport;
	OrthographicCamera camera;
	SpriteBatch batch;
	private int touchX, touchY;
	
	public DungeonScreen(){
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		DungeonLayout = new ArrayList<DungeonRoom>();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		buildMap(5);

		stage = new Stage();
		for(int i = 0; i < 5; i++){
			stage.addActor(DungeonLayout.get(i));
		}
		viewport = new FitViewport(0, 0, camera);
		camera.position.x = DungeonLayout.get(0).getX(Align.center);
		camera.position.y = DungeonLayout.get(0).getY(Align.center);
		stage.setViewport(viewport);
		Gdx.input.setInputProcessor(this);
	}
	
	private void buildMap(int size){
		ArrayList<DungeonRoom> noConnection = new ArrayList<DungeonRoom>();
		
		Random rand = new Random();
		int random = rand.nextInt(4) + 1;
		
		for(int i = 0; i < size; i++){
			noConnection.add(new DungeonRoom());
		}
		//Add Initial room to layout and remove it from connection list
		DungeonLayout.add(noConnection.get(0));
		noConnection.remove(0);
		size--;
		
		while(size > 0){
			int currentRoom = 0;
			//Make sure we don't add more rooms than we are suppose to
			while(random > size){
				random = rand.nextInt(4) + 1;
			}
			

		}
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		camera.update();
		stage.act();
		stage.draw();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Input.Keys.LEFT){
			camera.translate(-10, 0, 0);
			System.out.println("Left");
		}
		if(keycode == Input.Keys.RIGHT){
			camera.translate(10, 0, 0);
			System.out.println("Left");
		}
		if(keycode == Input.Keys.UP){
			camera.translate(0, 10, 0);
			System.out.println("Left");
		}
		if(keycode == Input.Keys.DOWN){
			camera.translate(0, -10, 0);
			System.out.println("Left");
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		touchX = screenX;
		touchY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		Vector2 lastTouch, newTouch, delta;
		lastTouch = new Vector2(touchX, touchY);
		newTouch = new Vector2(screenX, screenY);
		delta = new Vector2();
		delta = newTouch.cpy().sub(lastTouch);
		camera.position.x -= delta.x;
		camera.position.y += delta.y;
		touchX = (int) newTouch.x;
		touchY = (int) newTouch.y;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
