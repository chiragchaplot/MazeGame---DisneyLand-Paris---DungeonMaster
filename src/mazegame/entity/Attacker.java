package mazegame.entity;

public interface Attacker {
	void attack(Attacker target);
    void takeDamage(int damage);
    boolean isAlive();
}
