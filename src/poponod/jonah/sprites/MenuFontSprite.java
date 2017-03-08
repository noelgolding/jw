package poponod.jonah.sprites;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import l337.game.Game;
import l337.game.sprite.FontSprite;

public class MenuFontSprite extends FontSprite {
	/** eh, not really great */
	protected static final String _style_7_bubble_bead  = "fonts/style-7_bubble-pixel-7/bubble_pixel-7_bead.ttf";
	protected static final String _pixel_noir  = "fonts/pixel_noir/Pixel-Noir.ttf";
	protected static final String _pixel_noir_skinny_caps  = "fonts/pixel_noir/Pixel-Noir Skinny Caps.ttf";
	protected static final String _pixel_noir_skinny_short  = "fonts/pixel_noir/Pixel-Noir Skinny Short.ttf";
	protected static final String _pixel_noir_skinny  = "fonts/pixel_noir/Pixel-Noir Skinny.ttf";

	/** I kinda like these */
	protected static final String _v5xtender  = "fonts/v5xtender/V5Xtende.ttf";
	protected static final String _game_classic  = "fonts/freaky-fonts_gamegirl-classic/Gamegirl.ttf";
	protected static final String _style_7_half_bold  = "fonts/style-7_half-bold-pixel-7/half_bold_pixel-7.ttf";
	protected static final String _style_7_bubble  = "fonts/style-7_bubble-pixel-7/bubble_pixel-7.ttf";
	protected static final String _style_7_bubble_hatch  = "fonts/style-7_bubble-pixel-7/bubble_pixel-7_hatch.ttf";
	protected static final String _style_7_bubble_dark  = "fonts/style-7_bubble-pixel-7/bubble_pixel-7_dark.ttf";
	protected static final String _pixel_noir_caps  = "fonts/pixel_noir/Pixel-Noir Caps.ttf";
	protected static final String _8bitLimit  = "fonts/8-bit Limit_BRK.ttf"; // no apostrophe :(
	protected static final String _8bitOLimit  = "fonts/8-bit Limit O (BRK).ttf"; // no apostrophe :(

	
	/** My favorites */
	protected static final String _8bit_wonder  = "fonts/8bit_wonder/8-BIT WONDER.TTF"; // no apostrophe :(
	protected static final String _fipps  = "fonts/fipps/Fipps-Regular.otf";
	protected static final String _digiffiti  = "fonts/digiffiti.ttf"; // no apostrophe :(
	protected static final String _04b_30  = "fonts/04b_30/04B_30__.TTF";

	
	private static final String fontPath = _04b_30;
	private static Font menuFont = null;
	
	public MenuFontSprite(Game game, String string, Color color) {
		super(game, string, color, getMenuFont());
	}

	protected static Font getMenuFont() {
		if (menuFont == null) {
			try {
				
				InputStream source = MenuFontSprite.class.getClassLoader().getResourceAsStream(fontPath);
				File destination = new File(System.getProperty("java.io.tmpdir") + "/" + System.nanoTime() + ".tmp");
				
				if (destination.exists()) {
					if (destination.isDirectory()) {
						throw new IOException("File '" + destination + "' exists but is a directory");
					}
					if (destination.canWrite() == false) {
						throw new IOException("File '" + destination + "' cannot be written to");
					}
				} else {
					final File parent = destination.getParentFile();
					if (parent != null) {
						if (!parent.mkdirs() && !parent.isDirectory()) {
							throw new IOException("Directory '" + parent + "' could not be created");
						}
					}
				}
				final FileOutputStream output = new FileOutputStream(destination, false);
				byte[] buffer = new byte[1024*4];
				int n;
				while ((n = source.read(buffer)) != -1) {
					output.write(buffer, 0, n);
				}
				output.close();
				source.close();
				
				menuFont = Font.createFont(Font.TRUETYPE_FONT, destination);
			} catch (Exception e) {
				e.printStackTrace();
				menuFont = DEFAULT_FONT;
			}
		}
		return menuFont;
	}

}
