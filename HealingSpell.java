public class HealingSpell extends Action
{
	private int baseHeal;
	
	public HealingSpell(String name, int baseHeal)
	{
		super(name);
		this.baseHeal = baseHeal;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " casts " + getName());
		me.addHitPoints(baseHeal);
	}
}