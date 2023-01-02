package vdm.p1.androidengine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import vdm.p1.engine.IFileManager;

public final class AndroidFileManager implements IFileManager {
	private final Context context;

	public AndroidFileManager(Context context) {
		this.context = context;
	}

	/**
	 * Creates an input file stream.
	 *
	 * @param path The path to create the input stream for.
	 * @return A {@link InputStream} to write to.
	 */
	@Override
	public InputStream openInputFile(String path) throws FileNotFoundException {
		return context.openFileInput(path);
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
			return context.openFileOutput(path, Context.MODE_PRIVATE);
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
		StringBuilder result = new StringBuilder();

		try (InputStreamReader is = new InputStreamReader(context.getAssets().open(path))) {
			BufferedReader buffer = new BufferedReader(is);
			do {
				result.append(buffer.readLine());
				result.append('\n');
			} while (buffer.ready());

			is.close();
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
