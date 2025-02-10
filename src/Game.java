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
