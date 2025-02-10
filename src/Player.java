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

interface Enemy {
    void attack(Player player);
    int getExperienceReward();
    String getName();
}

class Skeleton implements Enemy {
    public void attack(Player player) {
        player.takeDamage(10);
    }
    public int getExperienceReward() {
        return 20;
    }
    public String getName() {
        return "Skeleton";
    }
}

class Zombie implements Enemy {
    public void attack(Player player) {
        player.takeDamage(15);
    }
    public int getExperienceReward() {
        return 30;
    }
    public String getName() {
        return "Zombie";
    }
}

class Vampire implements Enemy {
    public void attack(Player player) {
        player.takeDamage(25);
    }
    public int getExperienceReward() {
        return 50;
    }
    public String getName() {
        return "Vampire";
    }
}

class EnemyManager {
    private List<Enemy> enemies;

    public EnemyManager() {
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void engageCombat(Player player, ScoreManager scoreManager) {
        for (Enemy enemy : new ArrayList<>(enemies)) {
            System.out.println(player.getName() + " fights " + enemy.getName());
            enemy.attack(player);
            player.gainExperience(enemy.getExperienceReward());
            scoreManager.addScore(enemy.getExperienceReward());
            enemies.remove(enemy);
        }
    }
}

class ItemManager {
    private List<String> items;

    public ItemManager() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }

    public void pickUpItem(Player player, String item, ScoreManager scoreManager) {
        System.out.println(player.getName() + " picks up " + item + ".");
        if (item.equals("Gold Coin")) {
            player.gainExperience(5);
            scoreManager.addScore(5);
        } else if (item.equals("Health Elixir")) {
            player.heal(20);
        } else if (item.equals("Magic Scroll")) {
            player.gainExperience(15);
            scoreManager.addScore(15);
        }
        player.pickUpItem(item);
        items.remove(item);
    }
}

class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }
}

class Game {
    private Player player;
    private EnemyManager enemyManager;
    private ItemManager itemManager;
    private ScoreManager scoreManager;

    public Game() {
        this.player = new Player("Adventurer");
        this.enemyManager = new EnemyManager();
        this.itemManager = new ItemManager();
        this.scoreManager = new ScoreManager();
        setupGame();
    }

    private void setupGame() {
        enemyManager.addEnemy(new Skeleton());
        enemyManager.addEnemy(new Zombie());
        itemManager.addItem("Gold Coin");
        itemManager.addItem("Health Elixir");
    }

    public void play() {
        enemyManager.engageCombat(player, scoreManager);
        while (!itemManager.getItems().isEmpty() && player.isAlive()) {
            String item = itemManager.getItems().get(0);
            itemManager.pickUpItem(player, item, scoreManager);
        }
        if (player.isAlive()) {
            System.out.println("You survived with " + player.getExperience() + " XP and " + scoreManager.getScore() + " points!");
        } else {
            System.out.println("Game Over!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
