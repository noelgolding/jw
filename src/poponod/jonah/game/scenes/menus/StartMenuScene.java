package poponod.jonah.game.scenes.menus;

import java.awt.Color;
import java.awt.event.KeyEvent;

import l337.game.SceneBasedGame;
import poponod.jonah.game.scenes.levels._01_WalkTestScene;

public class StartMenuScene extends GameMenuScene  {	
	public StartMenuScene(SceneBasedGame game) {
		super(game, game.getTitle(), Color.MAGENTA, Color.CYAN);
		
		this.menuItems.add(new GameMenuItem("New Game", null));
		this.menuItems.add(new GameMenuItem("Continue", null));
		this.menuItems.add(new GameMenuItem("Options", null));
		this.menuItems.add(new GameMenuItem("Exit Game", null));
	}

//	@Override
//	public void init() {
//		super.init();
//		
//		// TODO create proper buttons
//		
////		FontSprite newGameBtn = new FontSprite(this.game, "New Game", Color.WHITE);
////		newGameBtn.setPosition(100, 150);
////		addSprite(newGameBtn);
////		// Continue button // TODO disable if no saved game
////		FontSprite continueBtn = new FontSprite(this.game, "Continue", Color.WHITE);
////		continueBtn.setPosition(100, 200);
////		addSprite(continueBtn);
////		// Options button
////		FontSprite optionsBtn = new FontSprite(this.game, "Options", Color.WHITE);
////		optionsBtn.setPosition(100, 250);
////		addSprite(optionsBtn);
////		// Exit Game button
////		FontSprite exitGameBtn = new FontSprite(this.game, "Exit Game", Color.WHITE);
////		exitGameBtn.setPosition(100, 300);
////		addSprite(exitGameBtn);
//	}
	
	// TODO onEnter add MouseListener
	// TODO onExit remove MouseListener
	
	@Override
	public void update(float delta) {
		// TODO be better
		if (game.keysdown.getOrDefault(KeyEvent.VK_ENTER, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_ENTER, false);
			setNextScene(new _01_WalkTestScene(game));
			end();
		}
		
		if (game.keysdown.getOrDefault(KeyEvent.VK_O, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_O, false);
			setNextScene(new OptionsMenuScene(game));
			end();
		}
	}

}
