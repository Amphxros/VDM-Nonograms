package es.ucm.vdm.logic;

public class MenuScene extends Scene{
    Logic mLogic_; // needed for changing the scene
    final String GAME_TITLE = "NONOGRAMS";
    final String DESC="A puzzle in which cells in a grid have to be colored or left blank according to numbers given at the side of the grid to reveal a hidden image.";
    public MenuScene(Logic logic){
        super();
        this.mLogic_=logic;
        //crear los elementos de la escena

    }

}
