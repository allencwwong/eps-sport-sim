package com.eps.sportsim;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DungeonRoom extends Button{

	Image roomImg, attributeImg;
	Texture roomTexture, attributeTexture;
	Stack stack;
	Table attributeContainer;
	HashMap<Integer, DungeonRoom> connections;
	int parentDirection;
	
	
	public DungeonRoom() {
		super(new ButtonStyle());
		parentDirection = 5;
		this.setSize(100, 100);
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
		
		connections = new HashMap<Integer, DungeonRoom>(4);

	}

	public boolean hasChildren(){
		return !connections.isEmpty();
	}

	public void addChildren(DungeonRoom room, ArrayList<Vector2> positions){
		Random random = new Random();
		int direction = random.nextInt(4);
		boolean overlapping = true;
		int failsafe = 0;
		
		//Find a valid direction, checks for parent, current rooms already connected and overlapping with other rooms
		while(connections.containsKey(direction) || direction == parentDirection || overlapping){
			direction = random.nextInt(4);
			switch(direction){
				case 0:
					overlapping = positions.contains(new Vector2(this.getX() + 0, this.getY() + 120));
					break;
				case 1:
					overlapping = positions.contains(new Vector2(this.getX() + 120, this.getY() + 0));
					break;
				case 2:
					overlapping = positions.contains(new Vector2(this.getX() + 0, this.getY() - 120));
					break;
				case 3:
					overlapping = positions.contains(new Vector2(this.getX() - 120, this.getY() + 0));
					break;
				default:
					break;
			}

			//If we can't find a valid direction after 30 loops, abort mission
			if(failsafe < 30){
				failsafe++;
			}else{
				return;
			}
			
		}
		switch(direction){
			case 0:
				connections.put(direction, room);
				//if(positions.)
				room.setPosition(this.getX() + 0, this.getY() + 120);
				positions.add(new Vector2(this.getX() + 0, this.getY() + 120));
				room.parentDirection = 2;
				break;
			case 1:
				connections.put(direction, room);
				room.setPosition(this.getX() + 120, this.getY() + 0);
				positions.add(new Vector2(this.getX() + 120, this.getY() + 0));
				room.parentDirection = 3;
				break;
			case 2:
				connections.put(direction, room);
				room.setPosition(this.getX() + 0, this.getY() - 120);
				positions.add(new Vector2(this.getX() + 0, this.getY() - 120));
				room.parentDirection = 0;
				break;
			case 3:
				connections.put(direction, room);
				room.setPosition(this.getX() - 120, this.getY() + 0);
				positions.add(new Vector2(this.getX() - 120, this.getY() + 0));
				room.parentDirection = 1;
				break;
			default:
				System.out.println("ERRORPRONE: ADDING TOO MANY DIRECTIONS");
				break;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}

}
