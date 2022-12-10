package vdm.p1.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public interface IFileManager {
	/**
	 * Creates an input file stream.
	 *
	 * @param path The path to create the input stream for.
	 * @return A {@link FileInputStream} to write to.
	 */
	FileInputStream openInputFile(String path);

	/**
	 * Creates an output file stream.
	 *
	 * @param path The path to create the output stream for.
	 * @return A {@link FileInputStream} to read from.
	 */
	FileOutputStream openOutputFile(String path);

	/**
	 * Reads all the contents of a file given the path.
	 *
	 * @param path The path of the file to read.
	 * @return The file's contents.
	 */
	String readFile(String path);
}
