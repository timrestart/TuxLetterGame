package game;

import env3d.Env;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

public class Tux extends EnvNode {

	private Env env;
	private Room room;
	private double scale;

	public Tux(Env env, Room room) {
		this.env = env;
		this.room = room;
		scale = 8.0;

		setScale(scale);
		// positionnement au milieu de la largeur de la room
		setX(room.getWidth() / 2);
		// positionnement en hauteur basé sur la taille de Tux
		setY(getScale() * 1.1);
		// positionnement au milieu de la profondeur de la room
		setZ(room.getDepth() / 2);

		setTexture("models/tux/tux.png");
		setModel("models/tux/tux.obj");
	}

	// tester si tux touche les bord de la room
	private Boolean testeRoomCollision(double x, double z) {
		Boolean col = false;
		if (x <= 0 + scale || x >= room.getWidth() - scale || z <= 0 + scale || z >= room.getDepth() - scale) {
			col = true;
		}

		return col;
	}

	// deplace tux en fonction de la touche presse
	// teste s il y a collision avec les bords de la room
	public void deplace() {
		if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
			// Haut
			this.setRotateY(180);
			this.setZ(this.getZ() - 1.0);
			if (testeRoomCollision(getX(), getZ())) {
				this.setZ(this.getZ() + 2.0);
			}
		}
		if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
			// Gauche
			this.setRotateY(-90);
			this.setX(this.getX() - 1.0);
			if (testeRoomCollision(getX(), getZ())) {
				this.setX(this.getX() + 2.0);
			}
		}
		if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou D
			// Droite
			this.setRotateY(90);
			this.setX(this.getX() + 1.0);
			if (testeRoomCollision(getX(), getZ())) {
				this.setX(this.getX() - 2.0);
			}
		}
		if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou S
			// Bas
			this.setRotateY(0);
			this.setZ(this.getZ() + 1.0);
			if (testeRoomCollision(getX(), getZ())) {
				this.setZ(this.getZ() - 2.0);
			}
		}
	}
}
