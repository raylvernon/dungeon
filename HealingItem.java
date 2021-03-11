// a HealingItem has a limited number of uses

public class HealingItem extends Action
{
	private int baseHeal, numUses;
	
	public HealingItem(String name, int baseHeal, int numUses)
	{
		super(name);
		this.baseHeal = baseHeal;
		this.numUses = numUses;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " uses their " + getName() + "(" + (numUses-1) + ")");
		numUses--;
		me.addHitPoints(baseHeal);
		if(numUses == 0)
			me.removeAction(this);
	}
}