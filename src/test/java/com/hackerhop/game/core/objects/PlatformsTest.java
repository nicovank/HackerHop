package com.hackerhop.game.core.objects;

import com.hackerhop.game.core.objects.platforms.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlatformsTest {

	World world = new World(new Vec2(0, -50));
	Platforms platforms = new Platforms(world);

	@Test
	void platformsShouldGenerateAtMost21Platforms(){
		int count = platforms.getCount();
		assertTrue(count <= 21, "at most 21 Platform objects must be generated");
	}

	@Test
	void platformsShouldGenerateAtLeast10Platforms(){
		int count = platforms.getCount();
		assertTrue(count >= 15, "at least 15 Platform objects must be generated");
	}

	@Test
	void platformsShouldStayInsideXBoundary(){
		for (PlatformGroup platformGroup : platforms.getPlatformGroups()){
			for (Platform platform : platformGroup.getPlatforms()){
				if (platform != null) {
					assertTrue(platform.getBody().getPosition().x <= 480
									&& platform.getBody().getPosition().x >= 0,
							"Platform objects must spawn between x = 0 and x = 480");
				}
			}
		}
	}

	@Test
	void platformsShouldSpawnAboveY0(){
		for (PlatformGroup platformGroup : platforms.getPlatformGroups()){
			for (Platform platform : platformGroup.getPlatforms()){
				if (platform != null) {
					assertTrue(platform.getBody().getPosition().y >= 0,
							"Platform objects must spawn above y = 0");
				}
			}
		}
	}
}
