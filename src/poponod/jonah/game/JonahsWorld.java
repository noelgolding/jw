package poponod.jonah.game;

import l337.game.SceneBasedGame;
import poponod.jonah.game.scenes.menus.StartMenuScene;

public class JonahsWorld extends SceneBasedGame {

	// TODO Game Global State goes here.
	// TODO possibly make HUD global, and each Scene can decide if it wants to render the HUD
	// TODO , think about making the HUD a model, and each Scene can define the view if so desired. This Game object would be the controller
	
	public JonahsWorld() {
		super("Jonah's World", 1024, 768);
	}

	@Override
	public void init() {		
		pushScene(new StartMenuScene(this));
		nextScene();
		
	}
}
