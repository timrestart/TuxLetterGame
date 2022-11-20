package game;

public class Room {

	private int depth;
	private int height;
	private int width;
	private String textureBottom;
	private String textureNorth;
	private String textureEast;
	private String textureWest;
	private String textureTop;
	private String textureSouth;

	public Room() {
		depth = 100;
		height = 60;
		width = 100;

		textureBottom = "textures/floor.png";
		textureNorth = "textures/floor.png";
		textureEast = "textures/floor.png";
		textureWest = "textures/floor.png";
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTextureBottom() {
		return textureBottom;
	}

	public void setTextureBottom(String textureBottom) {
		this.textureBottom = textureBottom;
	}

	public String getTextureNorth() {
		return textureNorth;
	}

	public void setTextureNorth(String textureNorth) {
		this.textureNorth = textureNorth;
	}

	public String getTextureEast() {
		return textureEast;
	}

	public void setTextureEast(String textureEast) {
		this.textureEast = textureEast;
	}

	public String getTextureWest() {
		return textureWest;
	}

	public void setTextureWest(String textureWest) {
		this.textureWest = textureWest;
	}

}
