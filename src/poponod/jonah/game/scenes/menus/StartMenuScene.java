package poponod.jonah.game.scenes.menus;

import java.awt.event.KeyEvent;

import l337.game.SceneBasedGame;
import poponod.jonah.game.scenes.levels._01_WalkTestScene;

public class StartMenuScene extends GameMenuScene  {	
	public StartMenuScene(SceneBasedGame game) {
		super(game, game.getTitle());
		
		this.menuItems.add(new GameMenuItem("Continue", null)); // TODO if no history, gray this out
		this.menuItems.add(new GameMenuItem("Restart", null)); // TODO if no history, label this as "Start"
		this.menuItems.add(new GameMenuItem("Options", null));
		this.menuItems.add(new GameMenuItem("Exit", null));
	}
	
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
		
		if (game.keysdown.getOrDefault(KeyEvent.VK_ALT, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_ALT, false);
			setNextScene(new OptionsMenuScene(game));
			end();
		}
	}

}
