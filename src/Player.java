import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private int health;
    private int experience;
    private List<String> inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.experience = 0;
        this.inventory = new ArrayList<>();
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    public void gainExperience(int xp) {
        experience += xp;
    }

    public void pickUpItem(String item) {
        inventory.add(item);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public void resetHealth() {
        this.health = 100;
    }

    public String getName() {
        return name;
    }
}
