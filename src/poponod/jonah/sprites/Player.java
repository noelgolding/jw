package poponod.jonah.sprites;

import java.awt.Image;
import java.awt.event.KeyEvent;

import l337.game.Game;
import l337.game.sprite.BitmapSprite;

public class Player extends BitmapSprite {
	private Image left = game.getAssetManager().getImage("player/Left_01.png");
	private Image right = game.getAssetManager().getImage("player/Right_01.png");
	private Image back = game.getAssetManager().getImage("player/Back_01.png");
	private Image front = game.getAssetManager().getImage("player/Front_01.png");
	
	private float speed = 128f;
	private float vx = 0;
	private float vy = 0;
	
	public Player(Game game, Image img) {
		super(game, img);
	}
	
	@Override
	public void update(float delta) {
		handle_inputs();
		update_pos(delta);
		collision_detection();
	}

	private void handle_inputs() {
		if (!game.getKeysDown().getOrDefault(KeyEvent.VK_LEFT, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_A, false)
				&& !game.getKeysDown().getOrDefault(KeyEvent.VK_RIGHT, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_D, false)) {
			vx = 0;
		} 
		
		if (!game.getKeysDown().getOrDefault(KeyEvent.VK_UP, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_W, false)
			&& ! game.getKeysDown().getOrDefault(KeyEvent.VK_DOWN, false) && !game.getKeysDown().getOrDefault(KeyEvent.VK_S, false)) {
			vy = 0;
		}
		
		
		if (game.getKeysDown().getOrDefault(KeyEvent.VK_LEFT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_A, false)) {
			setImg(left);
			vx = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_RIGHT, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_D, false)) {
			setImg(right);
			vx = speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_UP, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_W, false)) {
			setImg(back);
			vy = -speed;
		} else if (game.getKeysDown().getOrDefault(KeyEvent.VK_DOWN, false) || game.getKeysDown().getOrDefault(KeyEvent.VK_S, false)) {
			setImg(front);
			vy = speed;
		}
	}

	private void update_pos(float delta) {
		x += vx * delta;
		y += vy * delta;
	}

	private void collision_detection() {
		// TODO Auto-generated method stub
		
	}

}
