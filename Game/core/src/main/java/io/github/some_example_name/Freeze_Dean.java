package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Freeze_Dean {
    private Rectangle materialsArea;
    private boolean discovered = false;
    private boolean usedMaterials = false;
    private boolean freezeActive = false;
    private float freezeTimer = 0f;

    public Freeze_Dean(TiledMap tiledMap, String layerName) {
        for (MapObject obj : tiledMap.getLayers().get(layerName).getObjects()) {
            if (obj instanceof RectangleMapObject && "Materials".equalsIgnoreCase(obj.getName())) {
                materialsArea = ((RectangleMapObject) obj).getRectangle();
                break;
            }
        }
    }

    public void update(Player player, GameScreen gameScreen) {
        if (materialsArea == null || usedMaterials) return;

        Rectangle playerRect = new Rectangle(player.getPosition().x, player.getPosition().y, 16, 16);

        if (playerRect.overlaps(materialsArea)) {
            discovered = true;
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                useFreeze(gameScreen);
            }
        }

        if (freezeActive) {
            freezeTimer -= Gdx.graphics.getDeltaTime();
            if (freezeTimer <= 0) {
                freezeActive = false;
                gameScreen.unfreezeDeans();
            }
        }
    }

    private void useFreeze(GameScreen gameScreen) {
        gameScreen.freezeAllDeans();
        freezeActive = true;
        freezeTimer = 30f;
        usedMaterials = true;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        if (materialsArea == null) return;

        if (discovered && !usedMaterials) {
            font.draw(batch, "[E] Freeze Dean (30s)", materialsArea.x - 80, materialsArea.y + 40);
        }
        if (freezeActive) {
            font.draw(batch, "Deans Frozen for 30 seconds", materialsArea.x, materialsArea.y);
        }
    }

    public boolean isUsed() {
        return usedMaterials;
    }
}
