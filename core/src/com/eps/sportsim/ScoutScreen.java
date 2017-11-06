package com.eps.sportsim;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScoutScreen implements Screen {
	
	ArrayList<Athlete> FreeAgents = new ArrayList<Athlete>();
	Table nameTable, statTable, rootTable;
	ScrollPane scrollPane;
	Stage stage;
	Skin skin;
	ArrayList<TextButton> buttonArray;
	FileHandle handle;
	Label[] statText;
	Label[] statValue;
	int resource = 10000;
	TextButton scoutButton;
	Label scoutResource, nameLabel;
	static int currentAgent;
	TextButton draftButton;
	TextButton roosterButton;
	
	public ScoutScreen(){
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		handle = Gdx.files.internal("names.txt");
		
		String text = handle.readString();
		String nameArray[] = text.split("\\r?\\n");
		
		
		buttonArray = new ArrayList<TextButton>();
		scrollPane = new ScrollPane(null);
		stage = new Stage(new ScreenViewport());
		
		nameTable = new Table();
		nameTable.setSize(stage.getWidth(), stage.getHeight());
		nameTable.setPosition(0, stage.getHeight());
		nameTable.align(Align.center|Align.top);
		//nameTable.setDebug(true);
		
		scrollPane.setSize(stage.getWidth()/4, stage.getHeight());
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setPosition(0, 0);
		scrollPane.setWidget(nameTable);
		
		statTable = new Table();
		statTable.setSize((float) (stage.getWidth()*0.75), stage.getHeight());
		statTable.setPosition((float) (stage.getWidth()* 0.25), 0);
		statTable.align(Align.center|Align.top);
		//statTable.setDebug(true);
		
		scoutResource = new Label("Points: " + resource, skin);
		statTable.add(scoutResource).height(50).expandX().align(Align.topRight).colspan(2);
		statTable.row();
		
		nameLabel = new Label("", skin);
		statTable.add(nameLabel).align(Align.center);
		statTable.row();
		statTable.setDebug(true);
		
		statText = new Label[10];
		statValue = new Label[10];
		for(int i = 0; i < statText.length; i++){
			statText[i] = new Label("", skin);
			statTable.add(statText[i]).align(Align.left);
			
			statValue[i] = new Label("", skin);
			statTable.add(statValue[i]).align(Align.left);
			
			statTable.row();
		}
		
		scoutButton = new TextButton("Scout Cost: 500", skin);
		scoutButton.addListener(new ScoutClickListener(statText));
		statTable.add(scoutButton).align(Align.center);
		
		try {
			for(int i = 0; i < 100; i++){
				FreeAgents.add(new Athlete(i%5 + 1, 4));
				FreeAgents.get(i).setName(nameArray[i]);
				buttonArray.add(new TextButton(FreeAgents.get(i).getName(), skin));
				//buttonArray[i].setSize(200, 50);
				buttonArray.get(i).addListener(new onAgentClicked(i));
				nameTable.add(buttonArray.get(i));
				nameTable.row();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		roosterButton = new TextButton("My Rooster", skin);
		roosterButton.addListener(new RoosterListener());
		draftButton = new TextButton("Draft Now", skin);
		draftButton.addListener(new DraftListener());

		statTable.add(draftButton);
		statTable.add(roosterButton);
		
		stage.addActor(statTable);
		stage.addActor(scrollPane);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());
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
	
	private class onAgentClicked extends ChangeListener{

		private int iterator;
		
		public onAgentClicked(int i){
			iterator = i;
		}
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			// TODO Auto-generated method stub

				currentAgent = iterator;
				nameLabel.setText(FreeAgents.get(iterator).getName());
				statText[0].setText("Strength: ");
				statText[1].setText("Speed: ");
				statText[2].setText("Intelligence: ");
				
				statText[3].setText("Ball Handling: " );
				statText[4].setText("Passing: ");
				statText[5].setText("Shooting: ");
				
				statText[6].setText("Block Shot: ");
				statText[7].setText("Steal Ball: ");
				statText[8].setText("Intercept Pass: ");
				
				statText[9].setText("Instincts: ");
				
				statValue[0].setText(FreeAgents.get(iterator).getStat("strength").getValue());
				statValue[1].setText(FreeAgents.get(iterator).getStat("speed").getValue());
				statValue[2].setText(FreeAgents.get(iterator).getStat("intelligence").getValue());
				
				statValue[3].setText(FreeAgents.get(iterator).getStat("ballHandling").getValue());
				statValue[4].setText(FreeAgents.get(iterator).getStat("passing").getValue());
				statValue[5].setText(FreeAgents.get(iterator).getStat("shooting").getValue());
				
				statValue[6].setText(FreeAgents.get(iterator).getStat("blockShot").getValue());
				statValue[7].setText(FreeAgents.get(iterator).getStat("stealBall").getValue());
				statValue[8].setText(FreeAgents.get(iterator).getStat("interceptPass").getValue());
				
				statValue[9].setText(FreeAgents.get(iterator).getStat("instincts").getValue());
		}
		
	}
	
	private class ScoutClickListener extends ClickListener{
		//Label[] stats;

		public ScoutClickListener(Label[] statText){
			//stats = statText;
		}
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
			if(FreeAgents.get(currentAgent).hiddenStatFound()){
				resource -= 500;
				scoutResource.setText("Points: " + resource);
				int statSelector = (int) (Math.random() * 9);
				while(!FreeAgents.get(currentAgent).getStat(statSelector).isHidden()){
					statSelector = (int) (Math.random() * 10);
				}
				FreeAgents.get(currentAgent).getStat(statSelector).setHidden(false);
				statValue[statSelector].setText(FreeAgents.get(currentAgent).getStat(statSelector).getValue());
			}
		}
		
	}
	
	private class DraftListener extends ClickListener{

		public DraftListener(){
			
		}
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
			System.out.println("Draft Button clicked for " + FreeAgents.get(currentAgent).getName());
			SportSim.yourRoster.add(FreeAgents.get(currentAgent));
			
			buttonArray.get(currentAgent).remove();
			
			
		}
		
	}

	private class RoosterListener extends ClickListener{
		public RoosterListener(){
			System.out.println("allen!!");
		}
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
			System.out.println("clicked rooster!!");
			//game.setScreen(new RosterScreen(game));


		}
	}

}
