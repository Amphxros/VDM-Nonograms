package vdm.p1.logic.objects;

import vdm.p1.logic.objects.base.Button;

public final class CheckSolutionButton extends Button {
    private final Table table;

    public CheckSolutionButton(Table table) {
        super();
        this.table = table;
    }

    @Override
    public void onClick() {
        System.out.println("Checking solutions. Found: " + table.checkSolutions() + " error(s)!");
    }
}