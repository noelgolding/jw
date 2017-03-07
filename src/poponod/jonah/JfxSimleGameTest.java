package poponod.jonah;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import l337.game.JfxSimpleGame;
import l337.game.sprite.JfxRectSprite;

public class JfxSimleGameTest extends JfxSimpleGame {
	private static final String TITLE = "My Very Cool Super Game!";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final Color BG_COLOR = Color.ALICEBLUE;
	
	public JfxSimleGameTest() {
		super(TITLE, WIDTH, HEIGHT, BG_COLOR);
	}

	@Override
	public void init() {
		JfxRectSprite theHero = new JfxRectSprite(this) {
			@Override
			public void update(float delta) {
				// TODO do something more useful
				// move around the screen
				if (keysdown.getOrDefault(KeyCode.LEFT, false)) {
					this.x -= 1;
				} else if (keysdown.getOrDefault(KeyCode.RIGHT, false)) {
					this.x += 1;
				}
				
				if (keysdown.getOrDefault(KeyCode.UP, false)) {
					this.y -= 1;
				} else if (keysdown.getOrDefault(KeyCode.DOWN, false)) {
					this.y += 1;
				}
			}
		};
		
		addSpriteToLayer(1, theHero);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
