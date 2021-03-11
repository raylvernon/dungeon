public class CuttingWeapon extends Action
{
	int baseDamage;
	
    public CuttingWeapon(String name, int baseDamage)
	{
		super(name);
		this.baseDamage = baseDamage;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " Slashes at " + target.getName() + " with their " + getName());
		target.subtractHitPoints(baseDamage);
	}
}