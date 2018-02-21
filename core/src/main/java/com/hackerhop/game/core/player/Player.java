package com.hackerhop.game.core.player;

import com.hackerhop.game.core.utils.Direction;
import com.hackerhop.game.core.utils.Position;

public class Player {
    private Position p;
    private Direction direction;

    //Left and Right Movement
    private void move(Direction d) {
        if (d == Direction.LEFT) {
            p.setPosition(p.getX() - 1, p.getY());
        }
        if (d == Direction.RIGHT) {
            p.setPosition(p.getX() + 1, p.getY());
        }

        direction = d;

    }

}
