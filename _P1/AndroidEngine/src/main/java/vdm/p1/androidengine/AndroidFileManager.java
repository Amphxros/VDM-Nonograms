package vdm.p1.androidengine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import vdm.p1.engine.IFileManager;

public class AndroidFileManager implements IFileManager {
	private final Context context;

	public AndroidFileManager(Context context) {
		this.context = context;
	}

	@Override
	public FileInputStream openInputFile(String path) {
		FileInputStream file = null;
		try {
			file = context.openFileInput(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	@Override
	public FileOutputStream openOutputFile(String path) {
		FileOutputStream file = null;

		try {
			file = context.openFileOutput(path, Context.MODE_PRIVATE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

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
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result.toString();
	}
}
