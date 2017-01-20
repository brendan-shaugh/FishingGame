package com.bss.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bss.game.Assets;
import com.bss.game.DynamicGameObject;
import com.bss.game.World;

public class RareFish extends EnemyGameObject {

	public static Sprite s = new Sprite(Assets.rare);
	
	public RareFish(float x, float y, World world, int i) {
		super(x, y, world, i, 17f, 7f, 63f, 26f, s);
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
        if (o instanceof RareFish) {
            RareFish that = (RareFish) o;
            result = (this.getPosition().y == that.getPosition().y && this.getPosition().x == that.getPosition().x);
        }
        return result;
	}

}
