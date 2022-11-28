package vdm.p1.logic.objects;

import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;

public class ReadingTable extends GameObject {
	private Cell[][] cells;
	private boolean[][] solutions;
	private IFont font;
	private int rows;
	private int columns;


	public ReadingTable(String filename, IFont font) {
		this.font=font;
	}

	private void read(){

	}






}
