package poponod.jonah.sprites;

import java.awt.Color;
import java.awt.Font;

import l337.game.Game;
import l337.game.sprite.FontSprite;

public class MenuTitleFontSprite extends MenuFontSprite {
	private static final int style = Font.ITALIC | Font.BOLD;
	private static final float size = 54;
	

	public MenuTitleFontSprite(Game game, String string, Color color) {
		super(game, string, color);
	}
	
	@Override
	protected FontSprite setFont(Font font) {
		return super.setFont(getMenuFont().deriveFont(style, size));
	}

}
