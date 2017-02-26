package poponod.jonah.game.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;

import l337.game.SceneBasedGame;
import l337.game.sprite.FontSprite;
import l337.game.sprite.Sprite;

public class StartSplashScene extends SplashScene  {	
	
	
	public StartSplashScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {
		// set canvas background color
		game.getCanvas().setBackground(Color.red); // TODO: only desirable for testing.
		
		// TODO create proper buttons
		// TODO center Logo and buttons
		// Logo
		Sprite logo = new FontSprite(this.game, "Jonah's World", Color.WHITE);
		logo.setPosition(100, 100); 
		addSprite(logo);
		// New Game button
		Sprite newGameBtn = new FontSprite(this.game, "New Game", Color.WHITE);
		newGameBtn.setPosition(100, 150);
		addSprite(newGameBtn);
		// Continue button // TODO disable if no saved game
		Sprite continueBtn = new FontSprite(this.game, "Continue", Color.WHITE);
		continueBtn.setPosition(100, 200);
		addSprite(continueBtn);
		// Options button
		Sprite optionsBtn = new FontSprite(this.game, "Options", Color.WHITE);
		optionsBtn.setPosition(100, 250);
		addSprite(optionsBtn);
		// Exit Game button
		Sprite exitGameBtn = new FontSprite(this.game, "Exit Game", Color.WHITE);
		exitGameBtn.setPosition(100, 300);
		addSprite(exitGameBtn);
	}
	
	// TODO onEnter add MouseListener
	// TODO onExit remove MouseListener
	
	@Override
	public void update(float delta) {
		// TODO be better
		if (game.keysdown.getOrDefault(KeyEvent.VK_ENTER, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_ENTER, false);
			setNextScene(new MainWorldScene(game));
			end();
		}
	}

}
