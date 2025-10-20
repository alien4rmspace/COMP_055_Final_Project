package FinalProject.Player;

import com.badlogic.gdx.utils.Array;
import FinalProject.Interactables.Interactable;

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


