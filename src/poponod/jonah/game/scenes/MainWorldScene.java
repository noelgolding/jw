package poponod.jonah.game.scenes;

import java.awt.Color;
import java.awt.Image;

import l337.game.SceneBasedGame;
import l337.game.scene.AbstractScene;
import poponod.jonah.sprites.AnimatedPlayer;

public class MainWorldScene extends AbstractScene{

	public MainWorldScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {
		game.getCanvas().setBackground(Color.blue);// TODO: only desirable for testing.
		
		final int numFrames = 4;
		Image[] playerStartingAnimation = new Image[numFrames];
		for (int i = 0; i < numFrames; i++) {
			playerStartingAnimation[i] = game.getAssetManager().getImage(String.format("player/Front_%02d.png", i+1));
		}
		
		AnimatedPlayer jonah = new AnimatedPlayer(game, playerStartingAnimation);
		jonah.setPosition(game.getWidth()/2 - playerStartingAnimation[0].getWidth(null)/2, game.getHeight()/2 - playerStartingAnimation[0].getHeight(null)/2);
		addSprite(jonah);
		
	}
}
