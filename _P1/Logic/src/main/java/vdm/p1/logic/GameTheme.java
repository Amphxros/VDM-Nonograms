package vdm.p1.logic;

import java.util.Scanner;

import vdm.p1.engine.IEngine;

/**
 * Represents a level in the game.
 */
public final class GameTheme {
	private final String id;
	private final String name;
	private final int index;
	private String[] levels = null;

	public GameTheme(String id, String name, int index) {
		this.id = id;
		this.name = name;
		this.index = index;
	}

	/**
	 * Gets the theme's folder ID, will always be lowercase.
	 *
	 * @return The theme's ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the theme's name, may contain spaces.
	 *
	 * @return The theme's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the theme's index.
	 *
	 * @return The theme's index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets whether or not {@link #getLevels()} will return `null`. It is advised to check whether
	 * or not this method returns true before calling {@link #load(IEngine)}, only then it will be
	 * safe to read the levels.
	 *
	 * @return Whether or not {@link #getLevels()} will return `null`.
	 */
	public boolean loaded() {
		return levels != null;
	}

	/**
	 * Gets the loaded levels. It is recommended to check {@link #loaded()} beforehand.
	 *
	 * @return The loaded levels.
	 */
	public String[] getLevels() {
		return levels;
	}

	/**
	 * Gets the index of a specified level.
	 *
	 * @param level The level to get the index from.
	 * @return The index of the specified level.
	 */
	public int getLevelIndex(String level) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].equals(level)) return i;
		}

		return -1;
	}

	/**
	 * Loads the theme's levels into memory.
	 *
	 * @param engine The engine to load the metadata from.
	 */
	public void load(IEngine engine) {
		String content = engine.getFileManager().readFile(getDataPath());
		Scanner read = new Scanner(content);

		levels = new String[read.nextInt()];
		for (int i = 0; i < levels.length; i++) {
			levels[i] = read.next();
		}
	}

	/**
	 * Gets the path to the file where the theme's data is at.
	 *
	 * @return The path to the file where the theme's data is at.
	 */
	public String getDataPath() {
		return "levels/" + id + "/data";
	}

	/**
	 * Gets the path to the file where a level's data is at.
	 *
	 * @param level The ID of the level to get the data from.
	 * @return The path to the level's data file.
	 */
	public String getDataPath(String level) {
		return "levels/" + id + '/' + level;
	}

	/**
	 * Gets the path to the image file for the theme.
	 *
	 * @return The path to the theme's image.
	 */
	public String getImagePath() {
		return "image/" + id + "_theme.png";
	}
}
