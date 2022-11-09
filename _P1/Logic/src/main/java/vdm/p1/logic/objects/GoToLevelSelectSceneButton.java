package vdm.p1.logic.objects;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.LevelSelectScene;

public class GoToLevelSelectSceneButton extends Button {
    private final IEngine engine;

    public GoToLevelSelectSceneButton(IEngine engine) {
        super();
        this.engine = engine;
    }

    @Override
    public boolean onPrimaryAction(TouchEvent event) {
        Logic logic = (Logic) engine.getLogic();
        logic.changeScene(new LevelSelectScene(engine));
        return true;
    }
}
