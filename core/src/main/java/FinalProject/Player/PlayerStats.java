package FinalProject.Player;

public final class PlayerStats {
    private int level;
    private float strength;
    private float dexterity;
    private float intelligence;
    private float luck;
    private float charisma;

    public PlayerStats(){
        level = 1;
        strength = 1;
        dexterity = 1;
        intelligence = 1;
        charisma = 1;
        luck = 1;
    }

    public int getLevel(){
        return this.level;
    }

    public float getStrength(){
        return this.strength;
    }

    public float getDexterity(){
        return this.dexterity;
    }

    public float getIntelligence(){
        return this.intelligence;
    }

    public float getLuck(){
        return this.luck;
    }

    public float getCharisma(){
        return this.charisma;
    }
}
