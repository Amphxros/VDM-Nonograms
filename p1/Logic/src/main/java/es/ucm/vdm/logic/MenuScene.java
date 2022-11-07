package es.ucm.vdm.logic;

public class MenuScene extends Scene{
    NonogramLogic mLogic_; // needed for changing the scene
    final String GAME_TITLE = "NONOGRAMS";
    final String DESC="A puzzle in which cells in a grid have to be colored or left blank according to numbers given at the side of the grid to reveal a hidden image.";
    public MenuScene(NonogramLogic logic){
        super();
        this.mLogic_=logic;
        //crear los elementos de la escena
        Table t= new Table(0,0,3, mLogic_.mEngine_.getHeight()/3,mLogic_.mEngine_.getHeight()/3 );
        addGameObject(t);

    }

}
