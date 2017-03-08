package poponod.jonah.sprites;

import java.awt.Color;
import java.awt.Font;

import l337.game.Game;
import l337.game.sprite.FontSprite;

public class MenuItemFontSprite extends MenuFontSprite {

	public MenuItemFontSprite(Game game, String string, Color color) {
		super(game, string, color);
	}
	
	@Override
	protected FontSprite setFont(Font font) {
		return super.setFont(getMenuFont().deriveFont(36f));
	}

}
