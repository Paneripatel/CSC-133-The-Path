package com.mycompany.a2;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class Game extends Form{
	private GameWorld gameWorld;
	private MapView mapView;
	private ScoreView scoreView;
	
	
	//Game method will call play method to play the game.
	public Game() {
		gameWorld = new GameWorld();
		mapView = new MapView();
		scoreView = new ScoreView();
		gameWorld.addObserver(mapView);
		gameWorld.addObserver(scoreView);
		
		Vector<Button> buttons = new Vector<Button>();
		
		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);
		
		Label title = new Label("The Path Game");
		toolbar.setTitleComponent(title);
		
		AccelerateCommand accelerateCommand = new AccelerateCommand(gameWorld);
		addKeyListener('a', accelerateCommand);
		Button accelerateButton = new Button();
		accelerateButton.setCommand(accelerateCommand);
		buttons.add(accelerateButton);
		toolbar.addCommandToSideMenu(accelerateCommand);
		
		BrakeCommand brakeCommand = new BrakeCommand(gameWorld);
		addKeyListener('b', brakeCommand);
		Button brakeButton = new Button();
		brakeButton.setCommand(brakeCommand);
		buttons.add(brakeButton);
		
		LeftTurnCommand leftTurnCommand = new LeftTurnCommand(gameWorld);
		addKeyListener('l', leftTurnCommand);
		Button leftTurnButton = new Button();
		leftTurnButton.setCommand(leftTurnCommand);
		buttons.add(leftTurnButton);
		
		RightTurnCommand rightTurnCommand = new RightTurnCommand(gameWorld);
		addKeyListener('r', rightTurnCommand);
		Button rightTurnButton = new Button();
		rightTurnButton.setCommand(rightTurnCommand);
		buttons.add(rightTurnButton);
		
		CollideWithFlagCommand collideWithFlagCommand = new CollideWithFlagCommand(gameWorld);
		Button collideWithFlagButton = new Button();
		collideWithFlagButton.setCommand(collideWithFlagCommand);
		buttons.add(collideWithFlagButton);
		
		CollideWithSpiderCommand collideWithSpiderCommand = new CollideWithSpiderCommand(gameWorld);
		addKeyListener('k', collideWithSpiderCommand);
		Button collideWithSpiderButton = new Button();
		collideWithSpiderButton.setCommand(collideWithSpiderCommand);
		buttons.add(collideWithSpiderButton);
		
		CollideWithFoodStationCommand collideWithFoodStationCommand = new CollideWithFoodStationCommand(gameWorld);
		addKeyListener('e', collideWithFoodStationCommand);
		Button collideWithFoodStationButton = new Button();
		collideWithFoodStationButton.setCommand(collideWithFoodStationCommand);
		buttons.add(collideWithFoodStationButton);
		
		TickCommand tickCommand = new TickCommand(gameWorld);
		addKeyListener('t', tickCommand);
		Button tickButton = new Button();
		tickButton.setCommand(tickCommand);
		buttons.add(tickButton);
		
		ExitCommand exitCommand = new ExitCommand();
		toolbar.addCommandToSideMenu(exitCommand);
		
		SoundCommand soundCommand = new SoundCommand(gameWorld);
		CheckBox soundCB = new CheckBox("Sound");
		soundCB.getAllStyles().setBgTransparency(255);
		soundCB.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCB.setCommand(soundCommand);
		toolbar.addComponentToSideMenu(soundCB);
		
		AboutCommand aboutCommand = new AboutCommand();
		toolbar.addCommandToSideMenu(aboutCommand);
		
		HelpCommand helpCommand = new HelpCommand();
		toolbar.addCommandToRightBar(helpCommand);
		
		for(int i = 0; i < buttons.size(); i++) {
			buttons.elementAt(i).getUnselectedStyle().setBgTransparency(255);
			buttons.elementAt(i).getUnselectedStyle().setBgColor(ColorUtil.BLUE);
			buttons.elementAt(i).getUnselectedStyle().setFgColor(ColorUtil.WHITE);
			buttons.elementAt(i).getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
			buttons.elementAt(i).getAllStyles().setPadding(Component.TOP, 5);
			buttons.elementAt(i).getAllStyles().setPadding(Component.BOTTOM, 5);
		}
		
		this.setLayout(new BorderLayout());
		mapView.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.MAGENTA));
		this.add(BorderLayout.CENTER, mapView);
		this.add(BorderLayout.NORTH, scoreView);
		Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.add(BorderLayout.SOUTH, southContainer);
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.add(BorderLayout.EAST, eastContainer);
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.add(BorderLayout.WEST, westContainer);
		
		westContainer.add(accelerateButton);
		eastContainer.add(brakeButton);
		westContainer.add(leftTurnButton);
		eastContainer.add(rightTurnButton);
		southContainer.add(collideWithFlagButton);
		southContainer.add(collideWithFoodStationButton);
		southContainer.add(collideWithSpiderButton);
		southContainer.add(tickButton);
		

		this.show();
		gameWorld.init(mapView.getWidth(), mapView.getHeight());
	}
	
}