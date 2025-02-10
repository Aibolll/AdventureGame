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
