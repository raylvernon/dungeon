// Team 9 strategy refactor
// NPT
// Moving attack/heal/special moves into 'Action' strategy pattern class
import java.util.ArrayList;



public abstract class DungeonCharacter implements Comparable
{

	protected String name;
	protected int hitPoints;
	protected int attackSpeed;
	protected double chanceToHit;

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

//-----------------------------------------------------------------
//explicit constructor to initialize instance variables -- it is called
// by derived classes
	public DungeonCharacter(String name, int hitPoints, int attackSpeed,
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
			//System.out.println("Remaining Hit Points: " + hitPoints);

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
			System.out.println(getName() + " hit " +
								" for <" + hitPoints + "> points damage.");
			System.out.println(getName() + " now has " +
								getHitPoints() + " hit points remaining.");
			System.out.println();
		}//end else if

		if (this.hitPoints == 0)
			System.out.println(name + " has been killed :-(");


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
		// complications such as chanceToHit will have to be reintroduced...
		// boolean canAttack;
		// int damage;

		// canAttack = Math.random() <= chanceToHit;

		// if (canAttack)
		// {
			// damage = (int)(Math.random() * (damageMax - damageMin + 1))
						// + damageMin ;
			// opponent.subtractHitPoints(damage);



			// System.out.println();
		// }//end if can attack
		// else
		// {

			// System.out.println(getName() + "'s attack on " + opponent.getName() +
								// " failed!");
			// System.out.println();
		// }//end else

	}//end attack method

//-----------------------------------------------------------------



}//end class Character