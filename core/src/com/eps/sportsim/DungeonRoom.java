package com.eps.sportsim;


import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DungeonRoom extends Button{

	Image roomImg, attributeImg;
	Texture roomTexture, attributeTexture;
	Stack stack;
	Table attributeContainer;
	HashMap<String, DungeonRoom> connections;
	
	private int currentConnections = 0;
	
	public DungeonRoom() {
		super(new ButtonStyle());
		
		this.setSize(200, 200);
		stack = new Stack();
		roomTexture = new Texture("room.jpg");
		roomImg = new Image(roomTexture);
		roomImg.setSize(this.getWidth(), this.getHeight());
		
		attributeTexture = new Texture("question.png");
		attributeImg = new Image(attributeTexture);
		
		attributeContainer = new Table();
		attributeContainer.setSize(this.getWidth(), this.getHeight());
		attributeImg.setSize(this.getWidth()/4, this.getHeight()/4);

		attributeContainer.add(attributeImg).center().pad(this.getHeight()/3);
		
		stack.add(roomImg);
		stack.add(attributeContainer);
		this.add(stack);
		
		connections = new HashMap<String, DungeonRoom>(4);
		//this.add(attributeImg);
		// TODO Auto-generated constructor stub
	}



	public void addConnection(DungeonRoom room){
		Random random = new Random();
		int direction;
		if(currentConnections <= 4){
			direction = random.nextInt(4);
			while(connections.containsKey(Integer.toString(direction))){
				direction = random.nextInt(4);
			}
			connections.put(Integer.toString(direction), room);
			currentConnections++;
		}else{
			System.out.println("ERRORPRONE: ADDING TOO MANY ROOMS ERROR");
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}

}
