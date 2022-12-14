package vdm.p1.logic.objects;

import vdm.p1.engine.Palette;

public class PaletteObject {
	private Palette palette;
	private String name;
	private int price;

	public PaletteObject(String name, int price){
		palette= new Palette();
		this.name=name;
		this.price=price;
	}
	public Palette getPalette(){
		return palette;
	}
	public String getName(){
		return name;
	}
	public int getPrice(){
		return price;
	}
}
