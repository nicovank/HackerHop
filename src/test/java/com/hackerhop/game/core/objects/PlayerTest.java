package com.hackerhop.game.core.objects;

import com.hackerhop.game.core.player.Player;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    void playerShapeTest() {
        World world = new World(new Vec2(0, -50));
        Vec2 position = new Vec2(0, 10);

        Player p = new Player(world, position);
        Shape s = p.getBody().getFixtureList().m_shape;
        PolygonShape r = new PolygonShape();
        r.setAsBox(3,3);
        assertEquals(s.getType(), r.getType());
    }
}
