package game;

import env3d.Env;
import env3d.advanced.EnvNode;

public class Letter extends EnvNode {

	private Env env;
	private Room room;
	private char letter;
	private double scale;

	public Letter(Env env, Room room, char l, double x, double z) {
		this.env = env;
		this.room = room;
		letter = l;
		scale = 3.0;

		setScale(scale);
		setX(x);
		setY(getScale() * 1.1);
		setZ(z);

		if (l == ' ') {
			setTexture("models/letter/cube.png");
		} else {
			setTexture("models/letter/" + letter + ".png");
		}
		setModel("models/letter/cube.obj");
	}
}
