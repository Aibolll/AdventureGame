import java.util.ArrayList;
import java.util.List;

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
