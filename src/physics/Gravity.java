package physics;

import org.lwjgl.util.vector.Vector2f;

public class Gravity {

    private float magnitude;

    public Gravity(float magnitude){
        this.magnitude = (float) (9.81*magnitude);
    }

    public Vector2f Apply(float timeSinceLastFrame){
        Vector2f tempVec = new Vector2f();
        tempVec.x = 0f;
        tempVec.y = magnitude*timeSinceLastFrame;
        return tempVec;
    }
}
