import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Examples {

	Bird b = new Bird("Blue", 4, 3, new Coord2D(0, 0), true);
	Cat c = new Cat("Aria", 4, 20, new Coord2D(0, 0), true);
	Chinchilla ch = new Chinchilla("Dusty", 4, 20, new Coord2D(0, 0), 4);

	public Examples() {

	}

	/* ANIMAL TESTS */

	@Test
	public void testAnimalGetName() {
		assertEquals("Blue", b.getName());
		assertEquals("Aria", c.getName());
		assertEquals("Dusty", ch.getName());
	}

	@Test
	public void testAnimalGetWeightInOz() {
		assertEquals(3, b.getWeightInOz());
		assertEquals(20, c.getWeightInOz());
		assertEquals(24, ch.getWeightInOz());
	}

	@Test
	public void testAnimalGetAge() {
		assertEquals(4, b.getAge());
		assertEquals(4, c.getAge());
		assertEquals(4, ch.getAge());
	}

	@Test
	public void testAnimalEats() {
		Cat c2 = new Cat("Joe", 4, 20, new Coord2D(0, 0), false);

		assertEquals(1, b.eats("seeds"));
		assertEquals(2, c.eats("cans"));
		assertEquals(3, c.eats("treats"));
		assertEquals(1, c2.eats("cans"));
		assertEquals(3, ch.eats("pellets"));
		assertEquals(1, ch.eats("hay"));
	}

	/** ZONE Tests */

	@Test
	public void testBirdZone() {
		LinkedList<Bird> birds = new LinkedList<Bird>();
		birds.add(b);
		BirdZone bz = new BirdZone(birds);

		assertEquals(1, bz.petsInZone());
		assertEquals(b, bz.heaviestPet());
		assertEquals(36, bz.inHumanYears("Blue"));
		assertEquals(-1, bz.inHumanYears("Bob"));

		assertEquals(bz, bz.feedZone());

		assertEquals(bz, bz.restockPetFood("seeds", 10));
		assertEquals("Bird: 10 seeds", bz.getPantryLabel());

		bz.restockPetFood("seeds", 0);

		assertEquals(b, bz.getPet("Blue"));
		assertEquals("Blue", bz.closestPet(4, 5));
	}

	@Test
	public void testCatZone() {
		LinkedList<Cat> cats = new LinkedList<Cat>();
		cats.add(c);
		CatZone cz = new CatZone(cats);

		assertEquals(1, cz.petsInZone());
		assertEquals(c, cz.heaviestPet());
		assertEquals(24, cz.inHumanYears("Aria"));
		assertEquals(-1, cz.inHumanYears("Bob"));

		assertEquals(cz, cz.feedZone());

		assertEquals(cz, cz.restockPetFood("cans", 10));
		assertEquals("Cat: 10 cans, 0 treats", cz.getPantryLabel());

		assertEquals(cz, cz.restockPetFood("treats", 10));
		assertEquals("Cat: 10 cans, 10 treats", cz.getPantryLabel());

		cz.restockPetFood("cans", 0);
		assertEquals("Cat: 10 cans, 10 treats", cz.getPantryLabel());

		assertEquals(c, cz.getPet("Aria"));
		assertEquals("Aria", cz.closestPet(4, 5));
	}

	@Test
	public void testChinchillaZone() {
		LinkedList<Chinchilla> chinchillas = new LinkedList<Chinchilla>();
		chinchillas.add(ch);
		ChinchillaZone chz = new ChinchillaZone(chinchillas);

		assertEquals(1, chz.petsInZone());
		assertEquals(ch, chz.heaviestPet());
		assertEquals(40, chz.inHumanYears("Dusty"));
		assertEquals(-1, chz.inHumanYears("Bob"));
		assertEquals(chz, chz.feedZone());
		assertEquals(chz, chz.restockPetFood("pellets", 3));
		assertEquals(chz, chz.restockPetFood("hay", 1));
		assertEquals(chz, chz.feedZone());
		assertEquals(ch, chz.getPet("Dusty"));
		assertEquals(null, chz.getPet("Bob"));
		assertEquals("Chinchilla: 0 pellets, 0 hay", chz.getPantryLabel());
		chz.feedZone();
		assertEquals("Chinchilla: 0 pellets, 0 hay", chz.getPantryLabel());
		assertEquals("Dusty", chz.closestPet(4, 5));
	}

	// SUPER PET RESCUE TESTS

	@Test
	public void testTotalPets() {
		LinkedList<Zoneable> zones = new LinkedList<Zoneable>();
		zones.add(new BirdZone(new LinkedList<Bird>()));
		zones.add(new CatZone(new LinkedList<Cat>()));
		zones.add(new ChinchillaZone(new LinkedList<Chinchilla>()));
		SuperPetRescue spr = new SuperPetRescue(zones);

		assertEquals(0, spr.totalPets());
		assertNull(spr.getHeaviestPetsName());

		LinkedList<Bird> birds = new LinkedList<Bird>();
		birds.add(b);
		zones.set(0, new BirdZone(birds));
		assertEquals(1, spr.totalPets());

		LinkedList<Cat> cats = new LinkedList<Cat>();
		cats.add(c);
		zones.set(1, new CatZone(cats));
		assertEquals(2, spr.totalPets());

		LinkedList<Chinchilla> chinchillas = new LinkedList<Chinchilla>();
		chinchillas.add(ch);
		zones.set(2, new ChinchillaZone(chinchillas));
		assertEquals(3, spr.totalPets());
		assertEquals("Dusty", spr.getHeaviestPetsName());
	}
}
