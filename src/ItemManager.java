import java.util.ArrayList;
import java.util.List;

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

