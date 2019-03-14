package entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public interface Entity {
    public void Draw();
    public void setPos();
    public Vector2f getPos();
    public void ApplyForce(Vector2f force);
    public boolean isRemove();

}
