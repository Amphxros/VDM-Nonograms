package vdm.p1.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.logic.objects.PaletteItem;

public final class GameManager implements Serializable {
	private static final long serialVersionUID = 1L;
	PaletteItem currentPalette;
	private int lastUnlockedTheme = 0;
	private int lastUnlockedLevel = 0;
	private int LastUnlockedPalette = 0;
	private int money = 0;
	private transient GameTheme[] themes = null;
	private transient PaletteItem[] palettes = null;


	public static GameManager load(IEngine engine) {
		InputStream stream;

		try {
			stream = engine.getFileManager().openInputFile("save");
		} catch (Exception e) {	// FileNotFoundException??
			return new GameManager();
		}

		////// FILE SUS

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Generates the checksum of the save and compares it to the previous one
			try{
				// If the checksum doesnt exists an error is returned
				InputStream checksumFD = engine.getFileManager().openInputFile("checksum");
				String hashing = getFileChecksum(md, stream);
				String checksumValue = InputStreamToString(checksumFD);
				String checksum = checksumValue.substring(7);

				if (!hashing.equals(checksum))		// Compares both strings
				{
					throw new Exception("Invalid Data");
				}
			// Either the Checksum doesn't exists or the comparison wasn't successful
			} catch (Exception e){
				// New savefile will be created
				return new GameManager();
			}

		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}

		///// FILE VALIDATION: NON-SUS

		GameManager object = null;
		try {
			stream = engine.getFileManager().openInputFile("save");
			ObjectInputStream objectStream = new ObjectInputStream(stream);
			object = (GameManager) objectStream.readObject();
			objectStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	// Creates a hex chain with the checksum and converts to string given an open file
	private static String getFileChecksum(MessageDigest digest, InputStream file) throws IOException {

		// Temporary chunk of data to feed the digest
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Updates the digest with the chache
		while ((bytesCount = file.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		};

		// Bytes on the digest
		byte[] bytes = digest.digest();

		// Passes the digest to hexadecimal
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();	// Final checksum of the file
	}

	private static String InputStreamToString(InputStream stream) throws IOException {

		ByteArrayOutputStream result = new ByteArrayOutputStream();

		int DEFAULT_BUFFER_SIZE = 8192;

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int length;
		while ((length = stream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");
		/*
		int bufferSize = 1024;
		char[] buffer = new char[bufferSize];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
		for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
			out.append(buffer, 0, numRead);
		}
		return out.toString();
		*/
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
	 * @return the palettes array of the app
	 */
	public PaletteItem[] getPalettes() {
		return palettes;
	}

	/**
	 * @param index position of the array
	 * @return An instance of {@link PaletteItem} of the array
	 */
	public PaletteItem getPalette(int index) {
		return palettes[index];
	}

	public PaletteItem getCurrentPalette() {
		return currentPalette;
	}

	public void loadPalettes(IEngine engine) {
		String content = engine.getFileManager().readFile("palettes/palette");
		Scanner read = new Scanner(content);
		palettes = new PaletteItem[read.nextInt()];

		for (int i = 0; i < palettes.length; i++) {
			StringBuilder stringBuilder = new StringBuilder(read.next());
			while (!read.hasNextInt()) {
				stringBuilder.append(' ');
				stringBuilder.append(read.next());
			}

			PaletteItem paletteObject = new PaletteItem(stringBuilder.toString(), read.nextInt());
			for (int c = 0; c < 5; ++c) {
				paletteObject.getPalette().addColor(new Color(read.nextInt(16)));
			}

			palettes[i] = paletteObject;
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
			//return true;
		} catch (Exception e) {
			e.printStackTrace();
			//return false;
		}

		OutputStream checksum = engine.getFileManager().openOutputFile("checksum");
		if(checksum == null) return false;

		InputStream save;
		try {
			save = engine.getFileManager().openInputFile("save");
		} catch (Exception e) {	// FileNotFoundException??
			return false;
		}

		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			ObjectOutputStream objectStream = new ObjectOutputStream(checksum);
			String text = getFileChecksum(md, save);
			// The Checksum Result is written as a String
			objectStream.writeObject(text);
			objectStream.flush();
			objectStream.close();
			return true;

		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
