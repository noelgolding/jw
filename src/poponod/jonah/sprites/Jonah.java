package poponod.jonah.sprites;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import l337.game.Game;
import l337.game.sprite.AnimatedBitmapSprite;

public class Jonah extends AnimatedBitmapSprite{
	final int numFrames = 4;
	
	private float animElapsedTime = 0;

	private Image[] left = new Image[numFrames];
	private Image[] right = new Image[numFrames];
	private Image[] back = new Image[numFrames];
	private Image[] front = new Image[numFrames];
	
	private float speed = 96f;
	private float vx = 0;
	private float vy = 0;
	
	private int speed_multiplier;

	private boolean negative;
	
	public Jonah(Game game, Image[] frames, boolean negative) {
		super(game, frames, 5);
		this.negative = negative;
		Stream.of(left, right, back, front).forEach(a -> initAnimations(a)); 
	}
	
	public Jonah(Game game, Image[] frames) {
		this(game, frames, false);
	}
	
	private void initAnimations(Image[] anim){
		String direction = "Front";
		if (anim == left) {
			direction = "Left";
		} else if (anim == right) {
			direction = "Right";
		} else if (anim == back) {
			direction = "Back";
		}
		for (int i = 0; i < numFrames; i++) {
			anim[i] = game.getAssetManager().getImage(String.format("player/%s_%02d%s.png", direction, i+1, (negative ? "_negative" : "")));
		}
	}
	
	@Override
	public void update(float delta) {
		resetVelocity();
		handle_inputs();
		update_pos(delta);
	}

	private void resetVelocity() {
		vx = 0;
		vy = 0;
	}

	private void handle_inputs() {
		speed_multiplier = game.getKeysDown().getOrDefault(KeyEvent.VK_SHIFT, false) ? 5 : 1;
		if (game.getKeysDown().getOrDefault(KeyEvent.VK_LEFT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_A, false)) {
			setFrames(left);
			vx = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_RIGHT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_D, false)) {
			setFrames(right);
			vx = speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_UP, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_W, false)) {
			setFrames(back);
			vy = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_DOWN, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_S, false)) {
			setFrames(front);
			vy = speed;
		} else {
			currentKeyFrame = Math.random() >= .5 ? 0 : 2; // TODO implement State
		}		
	}

	private void update_pos(float delta) {
		animElapsedTime += delta;
		x += vx * delta * speed_multiplier;
		y += vy * delta * speed_multiplier;
		
		// TODO check collision_detection, then update key frame?
		collision_detection();
		
		if (vx != 0 || vy != 0){
			if (animElapsedTime >= 1/(fps*speed_multiplier)) { // TODO implement Animation
				currentKeyFrame++;
				animElapsedTime = 0;
			}
		}
		if (currentKeyFrame >= numFrames) {
			currentKeyFrame = 0;
		}
	}

	private void collision_detection() {
		// TODO Auto-generated method stub
	}
}
