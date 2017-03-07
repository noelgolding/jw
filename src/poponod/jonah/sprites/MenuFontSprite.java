package poponod.jonah.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import l337.game.Game;
import l337.game.sprite.FontSprite;

public class MenuFontSprite extends FontSprite {
	private static Font menuFont = null;
	
	public MenuFontSprite(Game game, String string, Color color) {
		super(game, string, color, getMenuFont());
	}

	private static Font getMenuFont() {
		if (menuFont == null) {
			try {
				
				InputStream source = MenuFontSprite.class.getClassLoader().getResourceAsStream("fonts/04B_30__.TTF");
				File destination = new File(new File(MenuFontSprite.class.getClassLoader().getResource("fonts/tmp").toURI()), + System.nanoTime() + ".tmp");
				
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
				
				
				menuFont = Font.createFont(Font.TRUETYPE_FONT, destination).deriveFont(36f);
			} catch (Exception e) {
				e.printStackTrace();
				menuFont = DEFAULT_FONT;
			}
		}
		return menuFont;
	}

}
