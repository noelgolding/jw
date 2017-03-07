package poponod.jonah.game.scenes.menus;

import java.awt.Color;
import java.util.stream.IntStream;

import l337.game.SceneBasedGame;
import l337.game.scene.AbstractMenuScene;
import poponod.jonah.sprites.MenuFontSprite;

public abstract class GameMenuScene extends AbstractMenuScene {
	private static final int VSPACE = 50;
	private int y = 0;
	
	public GameMenuScene(SceneBasedGame game, String menuTitle, Color fgColor, Color bgColor) {
		super(game, menuTitle, fgColor, bgColor);
	}
	
	@Override
	public void init() {
		// TITLE
		addSprite(new MenuFontSprite(game, menuTitle, fgColor)
				.setPosition(0, nextY())
				.centerAlign());
		
		// Add some space between TITLE and menu items.
		IntStream.of(1, 2, 3).forEach( i -> nextY());
		
		// for each menu item create the appropriate sprite.
		menuItems.stream()
			.forEach( i -> addSprite(new MenuFontSprite(game, i.getText(), fgColor).setPosition(0, nextY()).centerAlign()));
	}
	
	private int nextY(){
		return y += VSPACE;
	}
}
