package vdm.p1.androidengine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
		String result="";

		try{
			FileInputStream fin=context.openFileInput(file);
			InputStreamReader isr = new InputStreamReader(fin, StandardCharsets.UTF_8);
			BufferedReader buffer= new BufferedReader(isr);
			try{
				do{
					result+=buffer.readLine();
				}while(buffer.ready());

				isr.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			return result;
		}
		catch (Exception e){
			e.printStackTrace();
			InputStreamReader is;
			try {
				is= new InputStreamReader(context.getAssets().open(path + file));
				BufferedReader buffer= new BufferedReader(is);
				do{
					result+=buffer.readLine();
				}while(buffer.ready());

				is.close();

				return result;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}
}
