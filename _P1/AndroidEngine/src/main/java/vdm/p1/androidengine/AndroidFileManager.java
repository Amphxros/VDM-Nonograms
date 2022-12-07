package vdm.p1.androidengine;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import vdm.p1.engine.IFileMngr;

public class AndroidFileManager implements IFileMngr {
	private final Context context;
	public AndroidFileManager(Context context){
		this.context=context;
	}

	@Override
	public FileInputStream openInputFile(String path) {
		FileInputStream file=null;
		try{
			file= this.context.openFileInput(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return file;
	}

	@Override
	public FileOutputStream openOutputFile(String path) {
		FileOutputStream file=null;

		try{
			file= this.context.openFileOutput(path, Context.MODE_PRIVATE);
		}
		catch (Exception e){
			e.printStackTrace();
		}


		return file;
	}

	@Override
	public String readFile(String path, String file) {
		return null;
	}
}
