package es.ucm.vdm.logic;

public class Level extends Scene{
    Table table;

    public Level(int rows, int cols){
        super();
       table= new Table(rows, cols);
    }

    //reading from a file
    public Level(int n, String route){

    }


}
