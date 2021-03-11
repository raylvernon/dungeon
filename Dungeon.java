/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class is the driver file for the Heroes and Monsters project.  It will
  do the following:

  1.  Allow the user to choose a hero
  2.  Randomly select a monster
  3.  Allow the hero to battle the monster

  Once a battle concludes, the user has the option of repeating the above

*/
public class Dungeon
{
    public static void main(String[] args)
	{

		Hero theHero;
		Monster theMonster;
		theHero = chooseHero();
		int monstersKilled = 0;

		do
		{
			//once you kill 3 monsters you fight the dragon boss
			if(monstersKilled < 3)
				theMonster = generateMonster();
			else {
				System.out.println("you sense great danger ahead...");
				theMonster = new Dragon();
			}
			battle(theHero, theMonster);
			monstersKilled++;
			System.out.println("Monsters killed: " + monstersKilled);
		} while (playAgain());

    }//end main method

/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	public static Hero chooseHero()
	{
		int choice;
		Hero theHero;

		Hero heroes[] = {new Warrior(), new Sorceress(), new Thief()};
		
		System.out.println("Choose a hero:\n");
		for(int i = 0; i < heroes.length; i ++)
			System.out.println(i + ". " + heroes[i].getName());

		choice = Keyboard.readInt();
		return heroes[choice % heroes.length];
	}//end chooseHero method

/*-------------------------------------------------------------------
generateMonster randomly selects a Monster and returns it.  It utilizes
a polymorphic reference (Monster) to accomplish this task.
---------------------------------------------------------------------*/
	public static Monster generateMonster()
	{
		Monster monsters[] = {new Ogre(), new Gremlin(), new Skeleton()};

		int rnd = (int)(Math.random() * 100);
		
		return monsters[rnd % monsters.length];
	}//end generateMonster method

/*-------------------------------------------------------------------
playAgain allows gets choice from user to play another game.  It returns
true if the user chooses to continue, false otherwise.
---------------------------------------------------------------------*/
	public static boolean playAgain()
	{
		char again;

		System.out.println("Play again (y/n)?");
		again = Keyboard.readChar();

		return (again == 'Y' || again == 'y');
	}//end playAgain method

	
	
/*-----------------------------------------------
turns for heroes and monsters 
-------------------------------------------------*/
public static void takeTurn(DungeonCharacter active, DungeonCharacter target, boolean playerChooses)
{
	System.out.println(active.getName() + " is ready: ");
	
	// if the player is making the choice
	if(playerChooses)
	{
		// show available options
		for(int i = 0; i < active.getActions().size(); i ++)
			System.out.println(i + ". " + active.getActions().get(i).getName());

		active.act(Keyboard.readInt(), target);		
	}
	else // monster has to figure out what the best thing to do is
	{
		// choose randomly from available moves
		active.act((int)(Math.random()*100) % active.getActions().size(), target);
	}
}
	
/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	public static void battle(Hero theHero, Monster theMonster)
	{
		char pause = 'p';
		System.out.println(theHero.getName() + " vs. " +
							theMonster.getName());
		System.out.println("---------------------------------------------");

		int heroTimer    = 100;
		int monsterTimer = 100;
		
		//do battle
		while (theHero.isAlive() && theMonster.isAlive() && pause != 'q')
		{
			heroTimer -= theHero.getAttackSpeed();
			monsterTimer -= theMonster.getAttackSpeed();
			
			if(heroTimer <= 0)
			{
				// hero takes turn
				takeTurn((DungeonCharacter)theHero, (DungeonCharacter)theMonster, true);
				heroTimer = 100;
			}
			if(monsterTimer <= 0)
			{
				// monster takes turn
				takeTurn((DungeonCharacter)theMonster, (DungeonCharacter)theHero, false);
				monsterTimer = 100;
			}
		}//end battle loop

		
		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		else//both are alive so user quit the game
			System.out.println("Quitters never win ;-)");

	}//end battle method


}//end Dungeon class