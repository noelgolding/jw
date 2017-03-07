package poponod.jonah.game.scenes.levels;

import java.awt.Color;
import java.awt.Image;

import l337.game.SceneBasedGame;
import l337.game.sprite.SimpleBitmapSprite;
import poponod.jonah.game.scenes.GamePlayScene;
import poponod.jonah.sprites.Jonah;

public class _01_WalkTestScene extends GamePlayScene{
	public _01_WalkTestScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {
		game.getCanvas().setBackground(Color.green);
		// TODO implement TiledBasedScene... likely in the engine.
		// TODO implement YScrollableSpritesList ... likely in the engine.
		// TODO implement SpriteLayers ... likely in the engine.
		
		// TODO add background sprites
		// TODO add terrain sprites
		// TODO add collectibles and obstacles
		// TODO add enemies
		
		// init hero
		final int numFrames = 4;
		Image[] playerStartingAnimation = new Image[numFrames];
		for (int i = 0; i < numFrames; i++) {
			playerStartingAnimation[i] = assetMgr.getImage(String.format("player/Front_%02d.png", i+1));
		}
		
		Jonah jonah = new Jonah(game, playerStartingAnimation);
		jonah.setPosition(game.getWidth()/2 - playerStartingAnimation[0].getWidth(null)/2, game.getHeight()/2 - playerStartingAnimation[0].getHeight(null)/2);
		
		SimpleBitmapSprite simpleBitmapSprite = new SimpleBitmapSprite(game, assetMgr.getImage("misc/endSprite.png"));
		simpleBitmapSprite.rightAlign().middleAlign().canCollideWith(hero);
		initEnd(simpleBitmapSprite, _02_CameraTestScene.class.getName());
		
		initHero(jonah);
	}	
}
