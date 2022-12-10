package vdm.p1.pcengine;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import vdm.p1.engine.IFileManager;

public final class DesktopFileManager implements IFileManager {
	/**
	 * Creates an input file stream.
	 *
	 * @param path The path to create the input stream for.
	 * @return A {@link InputStream} to write to.
	 */
	@Override
	public InputStream openInputFile(String path) {
		try {
			return Files.newInputStream(Paths.get("Assets", path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates an output file stream.
	 *
	 * @param path The path to create the output stream for.
	 * @return A {@link OutputStream} to read from.
	 */
	@Override
	public OutputStream openOutputFile(String path) {
		try {
			return Files.newOutputStream(Paths.get("Assets", path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Reads all the contents of a file given the path.
	 *
	 * @param path The path of the file to read.
	 * @return The file's contents.
	 */
	@Override
	public String readFile(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get("Assets", path)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
