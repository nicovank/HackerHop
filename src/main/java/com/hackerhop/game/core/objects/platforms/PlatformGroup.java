package com.hackerhop.game.core.objects.platforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.dynamics.World;

import java.util.Random;

public class PlatformGroup implements GraphicsElement, Constants {

	private static final String TAG = PlatformGroup.class.getName();

	// Set of platforms
	private Platform[] platforms;
	private float y;
	private int xCount, wiggleRoom;


	/**
	 * Creates a new set of platforms, generating initial ones randomly.
	 *
	 * @param world The Box2D world of the platforms.
	 */
	public PlatformGroup(World world, float y, int wiggleRoom) {
		this.wiggleRoom = wiggleRoom;
		this.y = y;
		this.xCount = 3;

		platforms = generatePlatforms(world);
	}

	/**
	 * <p>Generates Platform objects for this PlatformGroup object.</p>
	 * <p>If this PlatformObject is at the base position, i.e. if <code>this.y == 0</code>,
	 * 9 Platform objects are generated to be used as the base platform.
	 * </p>
	 * <p>For all other cases, a maximum of 3 Platform objects are created at random
	 * within a circle of radius <code>wiggleRoom</code>. The center of each circle is separated from
	 * another by <code>GRID_SEPARATION</code> units.
	 * </p>
	 *
	 * @param w the physics world where Platform objects are to be generated
	 * @return
	 */
	private Platform[] generatePlatforms(World w) {
		Platform[] h;
		Random r = new Random();
		if (y != 0) {
			h = new Platform[xCount];
			for (int i = 0; i < xCount; ++i) {
				if (r.nextBoolean() || r.nextBoolean()) {
					h[i] = new Platform((GRID_SEPARATION * i) + 3.5f + (wiggleRoom - r.nextInt(2 * wiggleRoom)),
							(y * GRID_SEPARATION) + (wiggleRoom - r.nextInt(2 * wiggleRoom)), w);
				}
			}
		} else {
			h = new Platform[9];
			for (int i = 0; i < 54; i += 6) {
				h[i / 6] = new Platform(i, 0, w);
			}
		}
		return h;
	}

	/**
	 * Returns the y-value of the central grid
	 *
	 * @return y-value of the central grid
	 */
	public float getY() {
		return y * GRID_SEPARATION;
	}

	public Platform[] getPlatforms() {
		return platforms;
	}

	public int getCount() {
		return platforms.length;
	}

	@Override
	public void loadResources() {
		for (Platform p : platforms) {
			if (p != null) {
				p.loadResources();
			}
		}
	}

	@Override
	public void dispose() {
		for (Platform p : platforms) {
			if (p != null) p.dispose();
		}
	}

	public void destroy() {
		for (Platform p : platforms) {
			if (p != null) {
				p.destroy();
				p.dispose();
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Platform p : platforms) {
			if (p != null) p.render(batch);
		}
	}
}
