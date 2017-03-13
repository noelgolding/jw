package poponod.jonah.game.scenes;

import java.awt.Color;
import java.awt.event.KeyEvent;

import l337.game.SceneBasedGame;
import l337.game.scene.AbstractScene;
import l337.game.sprite.Sprite;
import l337.game.utils.KeyState;
import l337.game.utils.SceneFactory;
import poponod.jonah.game.scenes.menus.OptionsMenuScene;

public abstract class GamePlayScene extends AbstractScene {

	protected Sprite hero;
	protected Sprite endSceneSprite;
	protected String endScene;
	
	public GamePlayScene(SceneBasedGame game) {
		this(game, game.getWidth(), game.getHeight(), game.getCanvas().getBackground());
	}
	
	public GamePlayScene(SceneBasedGame game, int width, int height, Color bgColor) {
		super(game, width, height, bgColor);
		game.getViewport().setMargins(game.getWidth()/2, game.getHeight()/2);
	}
	
	@Override
	public void onEnter() {
		reset();
		super.onEnter();
	}
	
	@Override
	public void update(float delta) {
		if (game.keysdown.getOrDefault(KeyEvent.VK_ALT, false)) {
			// set the next scene
			// end current scene
			game.keysdown.put(KeyEvent.VK_ALT, false);
			setNextScene(new OptionsMenuScene(game));
			end();
		}

		
		super.update(delta);
		
		// TODO remove after testing, short circuit to get to next level
		if(endSceneSprite != null && game.getKeyState().get(KeyEvent.VK_PERIOD) == KeyState.JustPressed){
			hero.setPosition(endSceneSprite.getCenterX() - (hero.getWidth() / 2), endSceneSprite.getBottom() + 1);
		}
		
		// check for exit condition
		if (endSceneSprite != null && hero.collidedWith(endSceneSprite)){
			try {
				System.out.println(endScene);
				setNextScene(SceneFactory.get(endScene, game));
			} catch (Exception e) {
				throw new RuntimeException("Failed to set the next Scene: " + endScene, e);
			}
			end();
		}
		
		clampToScene(hero);
	}
	
	@Override
	public void onExit() {
		reset();
		// TODO maybe do some scene transition animation?
		super.onExit();
	}

	private void reset() {
		removeSprite(hero);
		removeSprite(endSceneSprite);
		hero = null;
		endSceneSprite = null;
	}
	
	protected void initEnd(Sprite sprite, String sceneClassName) {
		this.endSceneSprite = sprite;
		this.endScene = sceneClassName;
		addSprite(sprite);
	}
	
	protected void initHero(Sprite sprite) {
		this.hero = sprite;
		addSprite(sprite);
		game.getViewport().setFocus(sprite);
	}
}
