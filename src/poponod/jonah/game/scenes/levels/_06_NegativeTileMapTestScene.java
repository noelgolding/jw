package poponod.jonah.game.scenes.levels;

import java.awt.Color;
import java.awt.Image;

import l337.game.SceneBasedGame;
import l337.game.sprite.RectSprite;
import l337.game.sprite.SimpleBitmapSprite;
import l337.game.utils.tiled.TileMap;
import l337.game.utils.tiled.TileMap.TileSetListNullPointerException;
import l337.game.utils.tiled.TileSprite;
import l337.game.utils.tiled.TmxLoader;
import poponod.jonah.game.scenes.GamePlayScene;
import poponod.jonah.game.scenes.interiors._05_NegativeIndoorTestScene;
import poponod.jonah.sprites.Jonah;

public class _06_NegativeTileMapTestScene extends GamePlayScene{
	public _06_NegativeTileMapTestScene(SceneBasedGame game) {
		super(game);
	}

	@Override
	public void init() {
		game.getCanvas().setBackground(Color.white);

		TileMap tileMap = null;
		try {
			tileMap = TmxLoader.loadMap(game.getAssetManager(), "test_world_01_negative.tmx");
		} catch (TileSetListNullPointerException e) {
			throw new RuntimeException(e);
		}

		updateWorldDimensions(tileMap.getPixelWidth(), tileMap.getPixelHeight());
		// TODO add sprites to be drawn
		// first water, then grass
		
		RectSprite door = new RectSprite(game, 64, 16, 2239, 871, RectSprite.DEFAULT_COLOR);
		initEnd(door, _05_NegativeIndoorTestScene.class.getName());

		tileMap.getLayer("water").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
		tileMap.getLayer("grass").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
//		// TODO and buildings to YSortableSprites
//		tileMap.getLayer("buildings").stream().forEach(tile -> addSprite(new TileSprite(game, tile)));
		SimpleBitmapSprite castle = new SimpleBitmapSprite(game, assetMgr.getImage("misc/castle_negative.png"));
		castle.setPosition(2048, 480);
		addSprite(castle);
		SimpleBitmapSprite arch = new SimpleBitmapSprite(game, assetMgr.getImage("misc/arch_negative.png"));
		arch.setPosition(1888, 1264);
		addSprite(arch);
		
		// init hero
		final int numFrames = 4;
		Image[] playerStartingAnimation = new Image[numFrames];
		for (int i = 0; i < numFrames; i++) {
			playerStartingAnimation[i] = assetMgr.getImage(String.format("player/Front_%02d.png", i+1));
		}
		
		Jonah jonah = new Jonah(game, playerStartingAnimation);
		jonah.setPosition(1980, 1078);		
		initHero(jonah);
	}	
}
