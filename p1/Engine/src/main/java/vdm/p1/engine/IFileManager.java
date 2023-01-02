package vdm.p1.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IFileManager {
	/**
	 * Creates an input file stream.
	 *
	 * @param path The path to create the input stream for.
	 * @return A {@link InputStream} to write to.
	 */
	InputStream openInputFile(String path) throws FileNotFoundException, IOException;

	/**
	 * Creates an output file stream.
	 *
	 * @param path The path to create the output stream for.
	 * @return A {@link OutputStream} to read from.
	 */
	OutputStream openOutputFile(String path);

	/**
	 * Reads all the contents of a file given the path.
	 *
	 * @param path The path of the file to read.
	 * @return The file's contents.
	 */
	String readFile(String path);
}
