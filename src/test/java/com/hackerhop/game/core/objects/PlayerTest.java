package com.hackerhop.game.core.objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Character;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void withinBoundsWhenGameStartsTest() {
        World world = new World(new Vec2(0, -50));
        Vec2 position = new Vec2(0, 10);

        Player p = new Player(world, position);
        float x = p.getBody().getPosition().x;
        float y = p.getBody().getPosition().y;
        assertTrue(x >= 0 && x <= 54);
        assertTrue(y >= 0 && y <= 72);
    }

    @Test
    void floorExistsTest() {
        World world = new World(new Vec2(0, -50));
        Vec2 position = new Vec2(0, 10);

        Player p = new Player(world, position);
        world.step(100, 6, 2);

        float x = p.getBody().getPosition().x;
        float y = p.getBody().getPosition().y;
        assertTrue(x >= 0 && x <= 54);
        assertTrue(y >= 0 && y <= 72);
    }
    //testing player position at the start of the game.
    @Test
    void startingPositionTest(){
        World world = new World(new Vec2(0,-50));
        Vec2 position = new Vec2(0,10);
        Player p = new Player(world, position);
        float x = position.x;
        float y = position.y;
        assertTrue(x==0);
        assertTrue(y==10);
    }
    //testing player character
    @Test
    void characterTest(){
        World world = new World(new Vec2(0,-50));
        Vec2 position = new Vec2(0,10);
        Character character =Character.ROB;
        Player p = new Player(world, position,character);
        assertTrue(p.getCharacter() == character.ROB);

    }


}
