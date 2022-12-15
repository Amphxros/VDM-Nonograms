package vdm.p1.logic;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.Palette;
import vdm.p1.logic.objects.PaletteObject;

public final class GameManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private int lastUnlockedTheme = 0;
	private int lastUnlockedLevel = 0;
	private int LastUnlockedPalette=0;
	private int money = 0;
	private transient GameTheme[] themes = null;
	private transient PaletteObject[] palettes=null;
	PaletteObject currentPalette;

	public static GameManager load(IEngine engine) {
		InputStream stream;
		try {
			stream = engine.getFileManager().openInputFile("save");
		} catch (Exception e) {
			return new GameManager();
		}

		GameManager object = null;
		try {
			ObjectInputStream objectStream = new ObjectInputStream(stream);
			object = (GameManager) objectStream.readObject();
			objectStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	/**
	 * Gets the current level.
	 *
	 * @return The current unlocked level.
	 */
	public int getLastUnlockedLevel() {
		return lastUnlockedLevel;
	}

	/**
	 * Gets the current theme.
	 *
	 * @return The current unlocked theme.
	 */
	public int getLastUnlockedTheme() {
		return lastUnlockedTheme;
	}

	/**
	 * Gets the current amount of money.
	 *
	 * @return The current amount of money.
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Sets a new amount of money.
	 *
	 * @param money The new amount of money.
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Sets a level within a theme as completed.
	 *
	 * @param theme The theme the level is from.
	 * @param level The level that the user completed.
	 * @return Whether or not the data was updated.
	 */
	public boolean setCompleted(GameTheme theme, String level) {
		// If the theme that was completed was not the last one, the user replayed a previous theme.
		if (theme.getIndex() < getLastUnlockedTheme()) return false;

		// If the level the user played is not the last one, the user replayed a previous level.
		int index = theme.getLevelIndex(level);
		if (index < getLastUnlockedLevel()) return false;

		// If it's the last level...
		if (index == theme.getLevels().length - 1) {
			// ... and is also the last theme, then the user played the last level.
			if (theme.getIndex() == getThemes().length) return false;

			// Otherwise does a theme advance and sets the level to the first entry.
			lastUnlockedLevel = 0;
			lastUnlockedTheme++;
		} else {
			// The completed level was not the last one, advance to the next one within the theme.
			lastUnlockedLevel++;
		}

		return true;
	}

	/**
	 * Gets the loaded themes. It is recommended to check {@link #loaded()} beforehand.
	 *
	 * @return The loaded themes.
	 */
	public GameTheme[] getThemes() {
		return themes;
	}

	/**
	 * Gets a {@link GameTheme} by its index.
	 *
	 * @param index The index to retrieve the {@link GameTheme} from.
	 * @return The specified {@link GameTheme}.
	 */
	public GameTheme getTheme(int index) {
		return themes[index];
	}

	/**
	 * Gets a {@link GameTheme} by its ID.
	 *
	 * @param id The ID of the {@link GameTheme} to search.
	 * @return The specified {@link GameTheme}, if any.
	 */
	public GameTheme getTheme(String id) {
		for (GameTheme level : themes) {
			if (level.getId().equals(id)) return level;
		}

		return null;
	}

	/**
	 * Gets whether or not {@link #getThemes()} will return `null`. It is advised to check whether
	 * or not this method returns true before calling {@link #loadThemes(IEngine)}, only then it
	 * will be safe to read the themes.
	 *
	 * @return Whether or not {@link #getThemes()} will return `null`.
	 */
	public boolean loaded() {
		return themes != null;
	}

	public boolean loadedPalettes() {
		return palettes != null;
	}


	/**
	 * Load all the themes.
	 *
	 * @param engine Load all the available themes.
	 */
	public void loadThemes(IEngine engine) {
		String content = engine.getFileManager().readFile("levels/data");
		Scanner read = new Scanner(content);

		themes = new GameTheme[read.nextInt()];
		for (int i = 0; i < themes.length; i++) {
			String id = read.next();
			String name = read.nextLine().trim();
			themes[i] = new GameTheme(id, name, i);
		}
	}

	/**
	 *
	 * @return the palettes array of the app
	 */
	public PaletteObject[] getPalettes(){return palettes;}

	/**
	 * @param index position of the array
	 * @return An instance of {@link PaletteObject} of the array
	 */
	public PaletteObject getPalette(int index){return palettes[index];}

	/**
	 *
	 * @return the current rendering palette
	 */
	public PaletteObject getCurrentPalette() {
		return currentPalette;
	}

	public void setLastUnlockedPalette(int getLastUnlockedPalette) {
		this.LastUnlockedPalette = getLastUnlockedPalette;
	}

	public int getLastUnlockedPalette() {
		return LastUnlockedPalette;
	}

	public void loadPalettes(IEngine engine){
		String content= engine.getFileManager().readFile("palettes/palette");
		Scanner read= new Scanner(content);
		palettes= new PaletteObject[Integer.parseInt(read.nextLine())];

		for(int i=0; i<palettes.length; i++){
			String id= read.nextLine();
			String [] line= id.split(" ");
			PaletteObject paletteObject= new PaletteObject(line[0] + " "+ line[1],Integer.parseInt(line[2]));

			for(int j=3; j<line.length;j++){
				//Color c= new Color(Integer.parseInt(line[j]));
				//paletteObject.getPalette().addColor(c);
			}
			palettes[i]=paletteObject;


		}

	}

	/**
	 * Saves the current information into the save file.
	 *
	 * @param engine The engine that operates the GameManager.
	 * @return Whether or not the save was successful.
	 */
	public boolean save(IEngine engine) {
		OutputStream stream = engine.getFileManager().openOutputFile("save");
		if (stream == null) return false;

		try {
			ObjectOutputStream objectStream = new ObjectOutputStream(stream);
			objectStream.writeObject(this);
			objectStream.flush();
			objectStream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
