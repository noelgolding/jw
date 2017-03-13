package poponod.jonah.game.scenes.interiors;

import java.awt.Image;

import l337.game.SceneBasedGame;
import l337.game.sprite.SimpleBitmapSprite;
import l337.game.utils.tiled.TileMap;
import l337.game.utils.tiled.TileMap.TileSetListNullPointerException;
import l337.game.utils.tiled.TileSprite;
import l337.game.utils.tiled.TmxLoader;
import poponod.jonah.game.scenes.GamePlayScene;
import poponod.jonah.game.scenes.levels._06_NegativeTileMapTestScene;
import poponod.jonah.sprites.Jonah;

public class _04_IndoorTestScene extends GamePlayScene{

	public _04_IndoorTestScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {		
		TileMap tileMap = null;
		try {
			tileMap = TmxLoader.loadMap(game.getAssetManager(), "Test_world_inside.tmx");
		} catch (TileSetListNullPointerException e) {
			throw new RuntimeException(e);
		}

		updateWorldDimensions(tileMap.getPixelWidth(), tileMap.getPixelHeight());
				
		tileMap.getLayer("floor").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
		tileMap.getLayer("walls").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
		
		// init hero
		final int numFrames = 4;
		Image[] playerStartingAnimation = new Image[numFrames];
		for (int i = 0; i < numFrames; i++) {
			playerStartingAnimation[i] = assetMgr.getImage(String.format("player/Front_%02d.png", i+1));
		}
		
		Jonah jonah = new Jonah(game, playerStartingAnimation);
		jonah.setPosition(getWidth()/2 - playerStartingAnimation[0].getWidth(null)/2, getHeight()/2 - playerStartingAnimation[0].getHeight(null)/2);
		
		SimpleBitmapSprite simpleBitmapSprite = new SimpleBitmapSprite(game, assetMgr.getImage("misc/endSprite.png"));
		simpleBitmapSprite.centerAlign().middleAlign().canCollideWith(hero);
		initEnd(simpleBitmapSprite, _06_NegativeTileMapTestScene.class.getName());
		
		initHero(jonah);
	}

}
