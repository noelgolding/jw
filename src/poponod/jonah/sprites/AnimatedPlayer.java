package poponod.jonah.sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import l337.game.Game;
import l337.game.sprite.AnimatedBitmapSprite;
import poponod.jonah.game.constants.Direction;

public class AnimatedPlayer extends AnimatedBitmapSprite{
	final int numFrames = 4;
	
	private Image[] left = new Image[numFrames];
	private Image[] right = new Image[numFrames];
	private Image[] back = new Image[numFrames];
	private Image[] front = new Image[numFrames];
	
	private float speed = 96f;
	private float vx = 0;
	private float vy = 0;
	
	private Direction currentDirection, prevDirection;
	
	public AnimatedPlayer(Game game, Image[] frames) {
		super(game, frames, 5, true);
		this.currentDirection = Direction.S;
		this.prevDirection = Direction.S;
		Stream.of(left, right, back, front).forEach(a -> initAnimations(a)); 
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
			anim[i] = game.getAssetManager().getImage(String.format("player/%s_%02d.png", direction, i+1));
		}
	}
	
	@Override
	public void update(float delta) {
		handle_inputs();
		update_pos(delta);
		collision_detection();
	}

	private void handle_inputs() {
//		if (!game.getKeysDown().getOrDefault(KeyEvent.VK_LEFT, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_A, false)
//				&& !game.getKeysDown().getOrDefault(KeyEvent.VK_RIGHT, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_D, false)) {
//			vx = 0;
//		} 
//		
//		if (!game.getKeysDown().getOrDefault(KeyEvent.VK_UP, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_W, false)
//			&& ! game.getKeysDown().getOrDefault(KeyEvent.VK_DOWN, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_S, false)) {
//			vy = 0;
//		}
		
		
		vx = 0;
		vy = 0;
		if (game.getKeysDown().getOrDefault(KeyEvent.VK_LEFT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_A, false)) {
			setFrames(left);
			currentDirection = Direction.E;
			if (currentDirection != prevDirection)
				currentKeyFrame += Math.random() > .5 ? 1 : 3;
			vx = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_RIGHT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_D, false)) {
			setFrames(right);
			currentDirection = Direction.W;
			if (currentDirection != prevDirection)
				currentKeyFrame += Math.random() > .5 ? 1 : 3;
			vx = speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_UP, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_W, false)) {
			setFrames(back);
			currentDirection = Direction.N;
			if (currentDirection != prevDirection)
				currentKeyFrame += Math.random() > .5 ? 1 : 3;
			vy = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_DOWN, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_S, false)) {
			setFrames(front);
			currentDirection = Direction.S;
			if (currentDirection != prevDirection)
				currentKeyFrame += Math.random() > .5 ? 1 : 3;
			vy = speed;
		}
	}

	
	private float elapsedTime = 0;
	private void update_pos(float delta) {
		x += vx * delta;
		y += vy * delta;
		
		// check collision_detection, then update key frame?
		// if current direction is different that previous direction, set elapsedTime to 0
		elapsedTime += delta;
		if ((vx == 0 && vy == 0)) { //currentDirection != prevDirection || 
			elapsedTime = 0;
			currentKeyFrame = 0; // TODO change state
			currentDirection = null; // TODO fix hack
		} else if (vx != 0 || vy != 0){
			if (elapsedTime >= 1/fps) {
				System.out.println(String.format("elapsedTime: %f     frame: %d", elapsedTime, currentKeyFrame));
				currentKeyFrame++;
				elapsedTime = 0;
			}
		}
		if (currentKeyFrame >= numFrames) {
			currentKeyFrame = 0;
		}
		prevDirection = currentDirection;
	}

	private void collision_detection() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		Image img = getImg();
		int imgWidth = img.getWidth(null)*8;
		int imgHeight = img.getHeight(null)*8;
		int y = game.getHeight()/2 - imgHeight/2;
		
		g.drawImage(img, 0, y, imgWidth, imgHeight, null);
	}

}
