package poponod.jonah.game.scenes.menus;

import java.awt.Color;
import java.util.stream.IntStream;

import l337.game.SceneBasedGame;
import l337.game.scene.AbstractMenuScene;
import poponod.jonah.sprites.MenuItemFontSprite;
import poponod.jonah.sprites.MenuTitleFontSprite;

public abstract class GameMenuScene extends AbstractMenuScene {
	private static Color FG_COLOR = new Color(255, 197, 113);
	private static Color BG_COLOR = new Color(15, 87, 137);
	private static final int VSPACE = 50;
	private int y = 0;
	
	public GameMenuScene(SceneBasedGame game, String menuTitle) {
		super(game, menuTitle, FG_COLOR, BG_COLOR);
	}
	
	@Override
	public void init() {
		IntStream.of(1).forEach( i -> nextY());
		// TITLE
		addSprite(new MenuTitleFontSprite(game, menuTitle, fgColor)
				.setPosition(0, nextY())
				.centerAlign());
		
		// Add some space between TITLE and menu items.
		IntStream.of(1).forEach( i -> nextY());
		
		// for each menu item create the appropriate sprite.
		menuItems.stream()
			.forEach( i -> addSprite(new MenuItemFontSprite(game, i.getText(), fgColor).setPosition(0, nextY()).centerAlign()));
	}
	
	private int nextY(){
		return y += VSPACE*1.5;
	}
}
