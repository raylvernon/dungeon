public class Action
{
	public Action(String n)
	{
		name = n;
	}

	private boolean willHit(DungeonCharacter attacker, DungeonCharacter defender)
	{
		return attacker.getChanceToHit() > Math.random();
	}
	
	protected void attack(DungeonCharacter attacker, DungeonCharacter defender, int baseDamage)
	{
		if(willHit(attacker, defender))
			defender.subtractHitPoints(baseDamage);
		else
			System.out.println("- MISSED! -");
	}
	
    public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " waves their hand vaguely at " + target.getName() + ". are they casting a spell?");
	}

	protected String name;
	
	public String getName()
	{
		return name;
	}
	
	public @Override String toString()
	{
		return name;
	}
}