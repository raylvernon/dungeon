public class CrushingWeapon extends Action
{
	private int baseDamage;
	public CrushingWeapon(String name, int baseDamage)
	{
		super(name);
		this.baseDamage = baseDamage;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " swings their " + getName() + " down on " + target.getName() + "'s head!");
		target.subtractHitPoints(baseDamage);
	}
}