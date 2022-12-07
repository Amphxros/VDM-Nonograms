package vdm.p1.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
public interface IFileMngr {
	FileInputStream openInputFile(String path);
	FileOutputStream openOutputFile(String path);
	String readFile(String path, String file);

}
