package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Coin;
import com.hackerhop.game.core.scenes.GameScene;
import com.hackerhop.game.core.utils.Constants;
import com.hackerhop.game.core.utils.blinkers.SpriteBlinker;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactEdge;

import static com.hackerhop.game.core.utils.Random.randomInt;

public class ObstacleGenerator implements GraphicsElement, Constants {
    private static final String TAG = GameScene.class.getName();

    // Array indexes serve as grids
    // SCREEN_WIDTH / Obstacle.WIDTH gives 10 grids with spaces of width 20 on either side of the screen
    private Obstacle[] obstacles = new Obstacle[10];
    private int obstacleCount = 0;

    private World world;
    private Camera camera;

    private SpriteBlinker blinker;
    private Sprite arrow;

    private boolean coinSpawned = false;
    private Coin coin;

    public ObstacleGenerator(World world, Camera camera) {
        this.world = world;
        this.camera = camera;
    }

    /**
     * Will check if obstacles need deletion, and spawn new ones.
     */
    public int update() {
        int score = 0;
        float boundary = camera.position.y - SCREEN_HEIGHT;

        updateBlinker();

        // 1. Check if obstacles need deletion
        for (int i = 0; i < obstacles.length; ++i) {
            Obstacle obstacle = obstacles[i];

            if (obstacles[i] != null) {
                float obstacleY = obstacle.getBody().getPosition().y * PHYSICS_RATIO;

                if (obstacleY < boundary) {
                    destroyObstacle(obstacle);
                    obstacles[i] = null;
                    --obstacleCount;
                }
            }
        }

        // Coin spawning
        if (coinSpawned) {
            for (ContactEdge edge = coin.getBody().getContactList(); edge != null; edge = edge.next) {
                if (edge.other.getUserData().equals("player") && edge.contact.isTouching()) {
                    coin.destroy();
                    coin.dispose();
                    coinSpawned = false;
                    score = 2000;
                }
            }


            float coinY = coin.getBody().getPosition().y * PHYSICS_RATIO;

            if (coinY < boundary) {
                coin.destroy();
                coin.dispose();
                coinSpawned = false;
            }
        }

        // 2. generate between 1 and 4 obstacles.
        if (obstacleCount == 0) {
            for (int i = randomInt(4) + 1; i > 0; --i) {
                generateObstacle();
            }
        }
        return score;
    }

    public void destroyObstacle(Obstacle obstacle) {
        obstacle.dispose();
        obstacle.destroy();
    }

    /**
     * Generates a new obstacle if we have not yet reached the maximum number of obstacles.
     */
    public void generateObstacle() {

        int index = randomInt(obstacles.length);

        while (obstacles[index] != null) {
            index = randomInt(obstacles.length);
        }

        // There are 10 vertical lanes where Obstacles can be generated,
        // with a 2-unit-wide boundary on the left and right extremes of the screen, i.e.:
        //                     |                 |    ||||||||||    |                 |
        // (Left screen edge)->|[2-unit boundary]| ...[10 lanes]... |[2-unit boundary]|<-(Right screen edge)
        //                     |                 |    ||||||||||    |                 |

        float x = 2 + (5 * index);
        float y = (camera.position.y / PHYSICS_RATIO) + 500;

        obstacles[index] = new Obstacle(x, y, world);
        loadObstacle(obstacles[index]);
        ++obstacleCount;

        if (!coinSpawned || coin.getBody().getPosition().y < camera.position.y) {
            spawnCoin(x, y - 400);
        }

    }

    public void spawnCoin(float x, float y) {
        coin = new Coin(x, y , world);
        coinSpawned = true;
        coin.loadResources();
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }

    public void updateBlinker() {
        blinker.update();
    }

    public void loadObstacle(Obstacle obstacle) {
        obstacle.loadResources();
    }

    @Override
    public void loadResources() {
        arrow = new Sprite(new Texture("obstacles/arrow.png"));
        blinker = new SpriteBlinker(arrow, .5f, .5f);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (coinSpawned) {
            coin.update();
            coin.render(batch);
        }
        for (Obstacle obstacle : obstacles) {
            if (obstacle != null) {
                if (obstacle.getBody().getPosition().y > ((camera.position.y + (SCREEN_HEIGHT / 2)) / PHYSICS_RATIO)) {
                    blinker.render(batch,
                            obstacle.getBody().getPosition().x * PHYSICS_RATIO,
                            camera.position.y + (SCREEN_HEIGHT / 2) - 50
                    );
                } else {
                    obstacle.render(batch);
                }
            }
        }

    }

    @Override
    public void dispose() {

        arrow.getTexture().dispose();

        for (Obstacle obstacle : obstacles) {
            if (obstacle != null) {
                obstacle.destroy();
                obstacle.dispose();
            }
        }
    }
}
