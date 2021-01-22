package com.mygdx.game.utils;


import com.badlogic.gdx.physics.box2d.World;

public abstract class B2DObjects {

    public B2DObjects(World world)  {
        createBody(world);
    }

    abstract void createBody(World world);
}
