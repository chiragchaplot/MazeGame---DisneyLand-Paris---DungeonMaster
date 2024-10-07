package mazegame.entity;

public class Character {
	private int agility;
    private int lifePoints;
    private String name;
    private int strength;
    
//    public Mazegame.Entity.Dice m_Dice;
    
    public Character()
    {
    }

    public Character(String name)
    {
        this.setName(name);
    }

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public boolean isAlive() {
        return lifePoints > 0;
    }
	
	public void takeDamage(int damage) {
        lifePoints -= damage;
        if (lifePoints < 0) {
            lifePoints = 0;
        }
    }
}
