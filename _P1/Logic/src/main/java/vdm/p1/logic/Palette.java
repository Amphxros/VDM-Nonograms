package vdm.p1.logic;

import vdm.p1.engine.Color;

public class Palette {
	private Color bgColor;
	private Color selectColor;
	private Color markedColor;
	private Color fontColor;
	private Color wrongColor;

	public Palette(Color bg, Color select, Color marked, Color wrong, Color font){
		this.bgColor=bg;
		this.selectColor=select;
		this.markedColor=marked;
		this.wrongColor= wrong;
		this.fontColor=font;
	}
	public Color getBgColor(){return this.bgColor;}
	public Color getSelectColor(){return this.selectColor;}
	public Color getMarkedColor(){return this.markedColor;}
	public Color getWrongColor(){return this.wrongColor;}
	public Color getFontColor(){return this.fontColor;}
}
