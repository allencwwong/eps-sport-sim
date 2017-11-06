package com.eps.sportsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class RosterScreen implements Screen{

	Stage stage;
	Skin skin;
	Table table;
	Label textLabel;

	public RosterScreen(){
		stage = new Stage();
		//SportSim.yourRoster.get(0);

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		table = new Table();
		table.setSize(stage.getWidth(), stage.getHeight());
		table.setPosition(0, 0);
		table.align(Align.center|Align.top);
		table.row();
		table.setDebug(true);

		textLabel = new Label("test",skin);
		table.add(textLabel).align(Align.center);

		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		stage.act();
		stage.draw();
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

}
