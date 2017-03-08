package poponod.jonah.game.scenes.menus;

import java.awt.event.KeyEvent;

import l337.game.SceneBasedGame;

public class OptionsMenuScene extends GameMenuScene {
	private boolean musicOn = true;
	private boolean sfxOn = true;
	private boolean menuSoundsOn = true;
	
	public OptionsMenuScene(SceneBasedGame game) {
		super(game, "Options");
		
		
		
		/**
		 * 	Music on/off (maybe slider?)
	Sfx on/off (maybe slider?)
	Menu sounds on/off (maybe slider?)

		 */
		menuItems.add(new GameMenuItem("Music " + (musicOn ? "off" : "on"), null));
		menuItems.add(new GameMenuItem("Sound effects " + (sfxOn ? "off" : "on"), null));
		menuItems.add(new GameMenuItem("Menu sounds " + (menuSoundsOn ? "off" : "on"), null));
	}

//	@Override
//	public void init() {
//		super.init(); // TODO I don't want to have to remember to call super.init all the time.
//		// TODO Add menu items here.
//
//	}
	@Override
	public void update(float delta) {
		if (game.keysdown.getOrDefault(KeyEvent.VK_ESCAPE, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_ESCAPE, false);
			setNextScene(new StartMenuScene(game)); // should return to previous scene
			end();
		}
	}

}
