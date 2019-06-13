package little_fighters.content.materials;

import little_fighters.content.Content;
import little_fighters.content.materials.danger.FireStatue;
import little_fighters.content.materials.danger.Heal;
import little_fighters.content.materials.danger.HealStatue;
import little_fighters.content.materials.danger.LaserStatue;
import little_fighters.map.GameMachine;

public abstract class Material extends Content {

	public Material() {
		super();
	}

	public static Material copyMaterial(Material material) {
		if (material.getClass().isAssignableFrom(Cart.class)) {
			return new Cart(0, 0);
		}
		if (material.getClass().isAssignableFrom(Fence.class)) {
			return new Fence(0, 0);
		}
		if (material.getClass().isAssignableFrom(Tent.class)) {
			return new Tent(0, 0);
		}
		if (material.getClass().isAssignableFrom(Tree.class)) {
			return new Tree(0, 0);
		}
		if (material.getClass().isAssignableFrom(Tree1.class)) {
			return new Tree1(0, 0);
		}
		if (material.getClass().isAssignableFrom(Tree2.class)) {
			return new Tree2(0, 0);
		}
		if (material.getClass().isAssignableFrom(Wall.class)) {
			return new Wall(0, 0);
		}
		if (material.getClass().isAssignableFrom(WallGate.class)) {
			return new WallGate(0, 0);
		}
		if (material.getClass().isAssignableFrom(WallVertical.class)) {
			return new WallVertical(0, 0);
		}
		if (material.getClass().isAssignableFrom(Well.class)) {
			return new Well(0, 0);
		}
		if (material.getClass().isAssignableFrom(Wood.class)) {
			return new Wood(0, 0);
		}
		if (material.getClass().isAssignableFrom(FireStatue.class)) {
			return new FireStatue(0, 0);
		}
		if (material.getClass().isAssignableFrom(HealStatue.class)) {
			return new HealStatue(0, 0);
		}if (material.getClass().isAssignableFrom(LaserStatue.class)) {
			return new LaserStatue(0, 0);
		}
		return new Wall(0, 0);
	}

}
