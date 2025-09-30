package io.github.FinalProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

public class PlayerInteract {
    private final Array<Interactable> interactables;

    public  PlayerInteract(Array<Interactable> interactables) {
        this.interactables = interactables;
    }

    public void update(Player player){
        if (UserInputs.isInteractPressed()){
            for (Interactable interactable : interactables){
                if (interactable.canInteract(player)){
                    interactable.interact(player);
                    break;
                }
            }
        }
    }
}


