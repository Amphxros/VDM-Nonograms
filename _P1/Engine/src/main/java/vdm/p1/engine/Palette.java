package vdm.p1.engine;

import java.util.ArrayList;


public class Palette {
	ArrayList<Color> colors;

	public Palette(){
		colors= new ArrayList<>();
	}

	/**
	 * Adds a new Color
	 * @param color {@link Color} to add in the palette
	 */
	public void addColor(Color color){
		colors.add(color);
	}

	/**
	 *
	 * @param index position of the array
	 * @return {@Color} in the index position
	 */
	public Color getColor(int index){
		return colors.get(index);
	}

}
