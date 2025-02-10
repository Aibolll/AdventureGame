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

