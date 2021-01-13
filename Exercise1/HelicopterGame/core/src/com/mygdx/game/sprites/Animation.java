package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private Array<Sprite> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Array<Sprite> frameArray, float cycleTime) {
        frames = frameArray;
        this.frameCount = frames.size;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public void flip() {
        for (int i = 0; i < frames.size; i++) {
            frames.get(i).flip(true, false);
        }
    }

    public Sprite getFrame() {
        return frames.get(frame);
    }
}
