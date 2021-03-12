import java.util.ArrayList;



public class Dungeon
{
    public static void main(String[] args)
	{

		DungeonCharacter theHero;
		DungeonCharacter theMonster;
		
		theHero = chooseHero(chooseName() + " ");
		
		int monstersKilled = 0;

		do
		{
			if(monstersKilled > 0 && monstersKilled % 3 == 0){
				System.out.println("you sense great danger ahead...");
				theMonster = generateBoss();
			}else
				theMonster = generateMonster();
			
			battle(theHero, theMonster);
			monstersKilled++;
			System.out.println("Monsters killed: " + monstersKilled);
		} while (playAgain());

    }//end main method

	public static String chooseName()
	{
		System.out.println("What is your name, adventurer?");
		return Keyboard.readString();
	}
	
	
/*-------------------------------------------------------------------
chooseHero allows the user to select a DungeonCharacter, creates that DungeonCharacter, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	public static DungeonCharacter chooseHero(String name)
	{
		int choice;
		DungeonCharacter theHero;

		DungeonCharacter Heroes[] = {DungeonCharacter.Warrior(name), DungeonCharacter.Sorceress(name), DungeonCharacter.Thief(name)};
		
		System.out.println("What is your class?");
		
		return UI.promptOptions(Heroes);
	}//end chooseHero method

/*-------------------------------------------------------------------
generateMonster randomly selects a DungeonCharacter and returns it.  It utilizes
a polymorphic reference (Monster) to accomplish this task.
---------------------------------------------------------------------*/
	public static DungeonCharacter generateMonster()
	{
		DungeonCharacter Monsters[] = {DungeonCharacter.Ogre(), DungeonCharacter.Gremlin(), DungeonCharacter.Skeleton()};

		int rnd = (int)(Math.random() * 100);
		
		return Monsters[rnd % Monsters.length];
	}//end generateMonster method

	public static DungeonCharacter generateBoss()
	{
		return DungeonCharacter.Dragon();
	}
	
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
turns for DungeonCharacteres and DungeonCharacters 
-------------------------------------------------*/
public static void takeTurn(DungeonCharacter active, DungeonCharacter target, boolean playerChooses)
{
	System.out.println(active.getName() + " is ready: ");
	
	// if the player is making the choice
	if(playerChooses)
	{
		// choose action
		Action theAction = UI.promptOptions(active.getActions());
		// perform action
		theAction.perform(active, target);
	}
	else // DungeonCharacter has to figure out what the best thing to do is
	{
		// weighted probability for moves?
		// HP < N -> heal instead of attack?
		// choose randomly from available moves
		active.act((int)(Math.random()*100) % active.getActions().size(), target);
	}
}
	
/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a DungeonCharacter
and a DungeonCharacter to be passed in.  Battle occurs in rounds.  The DungeonCharacter
goes first, then the DungeonCharacter.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	public static void battle(DungeonCharacter theHero, DungeonCharacter theMonster)
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

			// Hero takes a turn
			if(heroTimer <= 0 && theHero.isAlive())
			{
				takeTurn((DungeonCharacter)theHero, (DungeonCharacter)theMonster, true);
				heroTimer = 100;
			}
			
			// Monster takes a turn
			if(monsterTimer <= 0 && theMonster.isAlive())
			{
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