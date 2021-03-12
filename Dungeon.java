import java.util.ArrayList;



public class Dungeon
{
    public static void main(String[] args)
	{

		DungeonCharacter theHero;
		DungeonCharacter theMonster;
		theHero = chooseHero();
		int monstersKilled = 0;

		do
		{
			//once you kill 3 DungeonCharacters you fight the dragon boss
			if(monstersKilled < 3)
				theMonster = generateMonster();
			else {
				System.out.println("you sense great danger ahead...");
				theMonster = DungeonCharacter.Dragon();
			}
			battle(theHero, theMonster);
			monstersKilled++;
			System.out.println("Monsters killed: " + monstersKilled);
		} while (playAgain());

    }//end main method

	// often we need a numbered list of available options for the user to choose
	public static <T> void printOptionList(T elems[])
	{
		for(int i = 0; i < elems.length; i++)
			System.out.println(i + ". " + elems[i]);
	}
	
	public static <T> void printOptionList(ArrayList<T> elems)
	{
		for(int i = 0; i < elems.size(); i++)
			System.out.println(i + ". " + elems.get(i));		
	}
/*-------------------------------------------------------------------
chooseHero allows the user to select a DungeonCharacter, creates that DungeonCharacter, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	public static DungeonCharacter chooseHero()
	{
		int choice;
		DungeonCharacter theHero;

		DungeonCharacter Heroes[] = {DungeonCharacter.Warrior(""), DungeonCharacter.Sorceress(""), DungeonCharacter.Thief("")};
		
		System.out.println("Choose a DungeonCharacter:\n");
		
		printOptionList(Heroes);
		
		choice = Keyboard.readInt();
		return Heroes[choice % Heroes.length];
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
		// show available options
		printOptionList(active.getActions());
		
		active.act(Keyboard.readInt(), target);		
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
			
			if(heroTimer <= 0)
			{
				// DungeonCharacter takes turn
				takeTurn((DungeonCharacter)theHero, (DungeonCharacter)theMonster, true);
				heroTimer = 100;
			}
			if(monsterTimer <= 0)
			{
				// DungeonCharacter takes turn
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