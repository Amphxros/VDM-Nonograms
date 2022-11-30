package vdm.p1.logic.objects;


import vdm.p1.engine.Color;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ReadingTable extends GameObject {
	private Cell[][] cells;
	private boolean[][] solutions;
	private IFont font;
	private int rows;
	private int columns;

	public ReadingTable(IFont font, String filename){
		this.font=font;
		read(filename);

		Grid grid = new Grid(rows, FlowDirection.VERTICAL);
		for(int i=0;i<rows;++i){
			Grid row = new Grid(columns, FlowDirection.HORIZONTAL);
			for(int j=0;j<columns;j++){
				Cell cell= new Cell(solutions[i][j]);
				row.setElement(j,cell);
				cells[i][j]=cell;
			}
			grid.setElement(i,row);
		}

		// Calculate the percentage of the grid's space within the table:
		// — The width of the grid is always the width of the table minus 20% → 80%:
		double gridWidth = 1.0 - 0.2;
		// — The height of the grid is always the width of the grid multiplied by its ratio:
		//   Examples:
		//     • 5x5  → 80% * (5 / 5)  = 80%
		//     • 5x10 → 80% * (5 / 10) = 40%
		double gridHeight = gridWidth * (rows / (double) columns);
		// — Calculate the margin top for the horizontal and grid Padding elements, this is
		//   accomplished by calculating 100% - gridHeight.
		double scaledMarginTop = 1.0 - gridHeight;

		// The Grid's padding is calculated by doing the following operations:
		// • Top    : scaledMarginTop
		// • Right  : 0%
		// • Bottom : 0%
		// • Left   : 20%              || matches LeftHint's left padding
		addChild(new Padding(scaledMarginTop, 0, 0, 0.2)
				.addChild(grid)
				.setStrokeColor(Color.BLACK));

		// The left hint padding is calculated by doing the following operations:
		// • Top    : scaledMarginTop  || matches Grid's top padding
		// • Right  : 80%              || matches the Table's width minus 20%
		// • Bottom : 0%
		// • Left   : 0%
		Grid hintLeftGrid = new Grid(rows, FlowDirection.VERTICAL);
		setHints(hintLeftGrid, getXHints(solutions));
		addChild(new Padding(scaledMarginTop, 0.8, 0, 0)
				.addChild(hintLeftGrid)
				.setStrokeColor(Color.BLACK));

		// The top hint padding is calculated by doing the following operations:
		// • Top    : 0.8 - gridHeight || matches Grid's top padding minus 20%
		// • Right  : 0%
		// • Bottom : gridHeight       || matches Grid's top padding
		// • Left   : 20%              || matches Grid's left padding
		Grid hintTopGrid = new Grid(columns, FlowDirection.HORIZONTAL);
		setHints(hintTopGrid, getYHints(solutions));
		addChild(new Padding(0.8 - gridHeight, 0, gridHeight, 0.2)
				.addChild(hintTopGrid)
				.setStrokeColor(Color.BLACK));
	}

	private void setHints(Grid grid, List<List<Integer>> lines) {
		FlowDirection hintDirection = grid.getDirection() == FlowDirection.HORIZONTAL ? FlowDirection.VERTICAL : FlowDirection.HORIZONTAL;

		for (int i = 0; i < lines.size(); ++i) {
			List<Integer> line = lines.get(i);
			int size = Math.max(4, line.size());
			Grid lineGrid = new Grid(size, hintDirection);
			for (int j = 0; j < line.size(); j++) {
				GameObject text = new Text(line.get(line.size() - j - 1).toString(), font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				lineGrid.setElement(size - j - 1, text);
			}

			grid.setElement(i, lineGrid);
		}
	}

	private List<List<Integer>> getXHints(boolean[][] solutions) {
		// [x][y]
		// ---

		List<List<Integer>> lines = new ArrayList<>(rows);
		for (int i = 0; i < rows; ++i) {
			Vector<Integer> line = new Vector<>();
			int count = 0;
			for (int j = 0; j < columns; ++j) {
				if (solutions[i][j]) {
					++count;
				} else if (count != 0) {
					line.add(count);
					count = 0;
				}
			}

			if (count != 0) line.add(count);
			lines.add(line);
		}

		return lines;
	}

	private List<List<Integer>> getYHints(boolean[][] solutions) {
		// [x][y]
		//    ---

		List<List<Integer>> lines = new ArrayList<>(columns);
		for (int j = 0; j < columns; ++j) {
			Vector<Integer> line = new Vector<>();
			int count = 0;
			for (int i = 0; i < rows; ++i) {
				if (solutions[i][j]) {
					++count;
				} else if (count != 0) {
					line.add(count);
					count = 0;
				}
			}

			if (count != 0) line.add(count);
			lines.add(line);
		}

		return lines;
	}



	/**
	 * reads a levelfile ESTO NO SE SI DEBERIA IR AQUI
	 * @param filename
	 */
	private void read(String filename){
		try {
			File file= new File(filename);
			Scanner read= new Scanner(file);
			String[][] aux= new String[rows][columns];
			int i=0;

			while (read.hasNextLine()) {
				String data = read.nextLine();
				String[] split= data.split(" ");
				if(split[0]=="Level"){
					this.rows= Integer.parseInt(split[1]);
					this.columns= Integer.parseInt(split[2]);
					aux= new String[rows][columns];
				}
				else{
					for(int j=0; j<columns;j++){
						aux[i][j]=split[j];
					}
					i++;
				}
			}
			cells= new Cell[rows][columns];
			solutions= new boolean[rows][columns];
			for(i=0;i<rows;i++){
				for(int j = 0;j < columns; j++){
					solutions[i][j]=aux[i][j]!="-";
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}






}
