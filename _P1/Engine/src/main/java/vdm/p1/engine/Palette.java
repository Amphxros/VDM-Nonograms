package vdm.p1.engine;

import java.util.ArrayList;


public class Palette {
	ArrayList<Color> colors;

	public Palette(){
		colors= new ArrayList<>();
	}
	public void addColor(Color color){
		colors.add(color);
	}
	public Color getColor(int index){
		return colors.get(index);
	}

}
