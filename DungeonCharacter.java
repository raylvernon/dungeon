// Team 9 strategy refactor
// NPT
// Moving attack/heal/special moves into 'Action' strategy pattern class
import java.util.ArrayList;



public class DungeonCharacter implements Comparable
{
	public static DungeonCharacter Warrior(String name)
	{
		DungeonCharacter rv = new DungeonCharacter(name + "the Warrior", 250, 4, .8);
		rv.addAction(new CuttingWeapon("Zweihander", 100)); // Barbarian bro goes WHACK
		rv.addAction(new HealingItem("Nordic Ale", 75, 3));
		rv.addAction(new CrushingWeapon("Moonlit Smasher", 110));
		return rv;
	}//end constructor

	public static DungeonCharacter Thief(String name)
	{
		DungeonCharacter rv = new DungeonCharacter(name + "the Thief", 150, 6, .8);
		rv.addAction(new CuttingWeapon("Crooked Dagger", 50));
		rv.addAction(new RangedWeapon("Dagger Throw", 35));
		rv.addAction(new HealingItem("Flask of Jin", 40, 3));
		return rv;
	}//end constructor

	public static DungeonCharacter Sorceress(String name)
	{
		DungeonCharacter rv = new DungeonCharacter(name + "the Sorceress", 150, 5, .7);
		rv.addAction(new RangedWeapon("Fireball", 100));
		rv.addAction(new HealingSpell("Heal", 50));
		rv.addAction(new RangedWeapon("Eldritch Blast", 155));
		return rv;
	}//end constructor

	public static DungeonCharacter Archer (String name){
		DungeonCharacter rv = new DungeonCharacter(name + "the Archer", 165, 5, .7);
		rv.addAction(new RangedWeapon("Longbow Shot", 90));
		rv.addAction(new RangedWeapon("Bomb Arrow", 110));
		rv.addAction(new HealingItem("Nectar Potion", 55, 3));
		return rv;
	}

    public static DungeonCharacter Ogre()
	{
		DungeonCharacter rv = new DungeonCharacter("Oscar the Ogre", 200, 2, .6);
		rv.addAction(new CrushingWeapon("Gnarled Oak Club", 80));
		rv.addAction(new HealingItem("Stinky Mead", 100, 1));
		return rv;
    }//end constructo

	public static DungeonCharacter Skeleton()
	{
		DungeonCharacter rv = new DungeonCharacter("Sargath the Skeleton", 100, 3, .8);
		rv.addAction(new RangedWeapon("Femur-braced bow", 60));
		return rv;
    }//end constructor

	public static DungeonCharacter Gremlin()
	{
		DungeonCharacter rv = new DungeonCharacter("Gnarltooth the Gremlin", 70, 5, .8);
		rv.addAction(new CuttingWeapon("Rusty Knife", 40));
		rv.addAction(new RangedWeapon("Blow darts", 20));
		return rv;
    }//end constructor

	public static DungeonCharacter Dragon() {
		DungeonCharacter rv = new DungeonCharacter("Drex the Dragon", 150, 3, .8);
		rv.addAction(new BreathAttack("Searing Flames", 100));
		rv.addAction(new CuttingWeapon("Talons", 80));
		rv.addAction(new CrushingWeapon("Thorny Tail", 80));
		return rv;
	}
	
	public static DungeonCharacter Mimic()
	{
		DungeonCharacter rv = new DungeonCharacter("a Loathsome, animate ooze", 100, 10, .5);
		rv.addAction(new Mimicry());
		return rv;
	}

	protected String name;
	protected int hitPoints;
	protected int attackSpeed;
	protected double chanceToHit;

	// important behavior is in actions!
	ArrayList<Action> actions;

	public void addAction(Action a)
	{
		actions.add(a);
	}

	public void removeAction(Action a)
	{
		// don't want to have an empty list of actions
		// this is intentionally exploitable by the player :)
		if(actions.size() > 1)
			actions.remove(a);
	}

	public ArrayList<Action> getActions()
	{
		return actions;
	}

	public int compareTo(Object o)
	{
		return 1;
	}

	public @Override String toString()
	{
		return name;
	}
	
//-----------------------------------------------------------------
//explicit constructor to initialize instance variables -- it is called
// by derived classes
	private DungeonCharacter(String name, int hitPoints, int attackSpeed,
				     double chanceToHit)
	{
		this.actions = new ArrayList<Action>();
		this.name = name;
		this.hitPoints = hitPoints;
		this.attackSpeed = attackSpeed;
		this.chanceToHit = chanceToHit;
	}//end constructor

//-----------------------------------------------------------------
	public String getName()
	{
		return name;
	}//end getName
	
//-----------------------------------------------------------------
	public int getHitPoints()
	{
		return hitPoints;
	}//end getHitPoints
//-----------------------------------------------------------------
	public int getAttackSpeed()
	{
		return attackSpeed;
	}//end getAttackSpeed

	public void setAttackSpeed(int sp)
	{
		attackSpeed = sp;
	}
/*-------------------------------------------------------
addHitPoints is used to increment the hitpoints a dungeon character has

Receives: number of hit points to add
Returns: nothing

This method calls: nothing
This method is called by: heal method of monsters and Sorceress
---------------------------------------------------------*/
	public void addHitPoints(int hitPoints)
	{
		if (hitPoints <=0)
			System.out.println("Hitpoint amount must be positive.");
		else
		{
			this.hitPoints += hitPoints;
			UI.printHP(this.hitPoints, hitPoints);
		}
	}//end addHitPoints method


/*-------------------------------------------------------
subtractHitPoints is used to decrement the hitpoints a dungeon character has.
It also reports the damage and remaining hit points (these things could be
done in separate methods to make code more modular ;-)

Receives: number of hit points to subtract
Returns: nothing

This method calls: nothing
This method is called by: overridden versions in Hero and Monster
---------------------------------------------------------*/
	public void subtractHitPoints(int hitPoints)
	{
		if (hitPoints <0)
			System.out.println("Hitpoint amount must be positive.");
		else if (hitPoints >0)
		{
			this.hitPoints -= hitPoints;
			if (this.hitPoints < 0)
				this.hitPoints = 0;
			
			UI.printHP(this.hitPoints, -hitPoints);
		}//end else if
	}//end method

/*-------------------------------------------------------
isAlive is used to see if a character is still alive by checking hit points

Receives: nothing
Returns: true is hero is alive, false otherwise

This method calls: nothing
This method is called by: unknown (intended for external use)
---------------------------------------------------------*/
    public boolean isAlive()
	{
	  return (hitPoints > 0);
	}//end isAlive method

// act(int action, DungeonCharacter target)
// 		action: what action to perform (index in actions[])
//      target: what DungeonCharacter to attack or target (behavior depends on action)
// dispatches on the actions[] array.
// knowing what number corresponds to what action requires
// querying the available actions with getActions()
	public void act(int actionChoice, DungeonCharacter target)
	{
		if(actions.size() > 0)
			actions.get(actionChoice % actions.size()).perform(this, target);
		else
			System.out.println(" but " + name + " doesn't know how to do anything!");
	}//end attack method

//-----------------------------------------------------------------



}//end class Character
