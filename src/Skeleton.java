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

