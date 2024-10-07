package mazegame.entity.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import mazegame.entity.Character;

class CharacterTest {
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("Test Character");
    }

    @Test
    void testSetAndGetAgility() {
        character.setAgility(10);
        assertEquals(10, character.getAgility(), "Agility should be set to 10");
    }

    @Test
    void testSetAndGetLifePoints() {
        character.setLifePoints(20);
        assertEquals(20, character.getLifePoints(), "Life points should be set to 20");
    }

    @Test
    void testSetAndGetName() {
        assertEquals("Test Character", character.getName(), "Name should be 'Test Character'");
    }

    @Test
    void testSetAndGetStrength() {
        character.setStrength(15);
        assertEquals(15, character.getStrength(), "Strength should be set to 15");
    }

    @Test
    void testIsAliveWithPositiveLifePoints() {
        character.setLifePoints(10);
        assertTrue(character.isAlive(), "Character should be alive when life points are greater than zero");
    }

    @Test
    void testIsAliveWithZeroLifePoints() {
        character.setLifePoints(0);
        assertFalse(character.isAlive(), "Character should not be alive when life points are zero");
    }

    @Test
    void testIsAliveWithNegativeLifePoints() {
        character.setLifePoints(-5);
        assertFalse(character.isAlive(), "Character should not be alive when life points are negative");
    }

    @Test
    void testTakeDamageReducesLifePoints() {
        character.setLifePoints(20);
        character.takeDamage(5);
        assertEquals(15, character.getLifePoints(), "Life points should reduce by 5 after taking 5 damage");
    }

    @Test
    void testTakeDamageDoesNotReduceLifePointsBelowZero() {
        character.setLifePoints(5);
        character.takeDamage(10);
        assertEquals(0, character.getLifePoints(), "Life points should not drop below zero");
    }

    @Test
    void testTakeDamageWithZeroLifePoints() {
        character.setLifePoints(0);
        character.takeDamage(5);
        assertEquals(0, character.getLifePoints(), "Life points should remain zero when already at zero");
    }
}
