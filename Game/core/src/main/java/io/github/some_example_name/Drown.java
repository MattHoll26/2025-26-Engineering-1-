package io.github.some_example_name;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * <code>Drown</code> represents a water hazard that resets the player
 * if they step into it. Uses the same rectangle overlap style
 * as bus and ticket interactions.
 */
public class Drown {

    private Rectangle waterArea;
    private Vector2 respawnPosition;

    /**
     * Constructor for <code>Drown</code>.
     *
     * @param tiledMap
     * @param layerName //Events
     * @param respawnX
     * @param respawnY
     */
    public Drown(TiledMap tiledMap, String layerName, float respawnX, float respawnY) {

        this.respawnPosition = new Vector2(respawnX, respawnY);

        for (MapObject obj : tiledMap.getLayers().get(layerName).getObjects()) {
            if (obj instanceof RectangleMapObject) {
                if ("Water".equalsIgnoreCase(obj.getName())) {
                    waterArea = ((RectangleMapObject) obj).getRectangle();
                    break;
                }
            }
        }
    }

    /**
     * Check if player has entered water and respawn
     */
    public boolean update(Player player) {
        if (waterArea == null) return false;

        Rectangle playerRect = new Rectangle(
            player.getPosition().x,
            player.getPosition().y,
            16,
            16
        );

        if (playerRect.overlaps(waterArea)) {
            player.getPosition().set(respawnPosition);
            return true; // drowned
        }

        return false;
    }

}
