package poponod.jonah.game.scenes.levels;

import java.awt.Color;
import java.awt.Image;

import l337.game.SceneBasedGame;
import l337.game.sprite.SimpleBitmapSprite;
import l337.game.utils.tiled.TileMap;
import l337.game.utils.tiled.TileMap.TileSetListNullPointerException;
import l337.game.utils.tiled.TileSprite;
import l337.game.utils.tiled.TmxLoader;
import poponod.jonah.game.scenes.GamePlayScene;
import poponod.jonah.game.scenes.interiors._04_IndoorTestScene;
import poponod.jonah.sprites.Jonah;

public class _03_TileMapTestScene extends GamePlayScene{
	public _03_TileMapTestScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {
		game.getCanvas().setBackground(Color.white);

		TileMap tileMap = null;
		try {
			tileMap = TmxLoader.loadMap(game.getAssetManager(), "test_world_01.tmx");
		} catch (TileSetListNullPointerException e) {
			throw new RuntimeException(e);
		}

		updateWorldDimensions(tileMap.getPixelWidth(), tileMap.getPixelHeight());
		// TODO add sprites to be drawn
		// first water, then grass
		
		tileMap.getLayer("water").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
		tileMap.getLayer("grass").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
//		// TODO and buildings to YSortableSprites
		tileMap.getLayer("buildings").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
//				
		
		// init hero
		final int numFrames = 4;
		Image[] playerStartingAnimation = new Image[numFrames];
		for (int i = 0; i < numFrames; i++) {
			playerStartingAnimation[i] = assetMgr.getImage(String.format("player/Front_%02d.png", i+1));
		}
		
		Jonah jonah = new Jonah(game, playerStartingAnimation);
		jonah.setPosition(getWidth()/2 - playerStartingAnimation[0].getWidth(null)/2, getHeight()/2 - playerStartingAnimation[0].getHeight(null)/2);
		
		SimpleBitmapSprite simpleBitmapSprite = new SimpleBitmapSprite(game, assetMgr.getImage("misc/endSprite.png"));
		simpleBitmapSprite.rightAlign().bottomAlign().canCollideWith(hero);
		initEnd(simpleBitmapSprite, _04_IndoorTestScene.class.getName());
		
		initHero(jonah);
	}	
}
