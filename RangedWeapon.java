public class RangedWeapon extends Action
{
	int baseDamage;
	
    public RangedWeapon(String name, int baseDamage)
	{
		super(name);
		this.baseDamage = baseDamage;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " Fires at " + target.getName() + " with their " + getName());
		attack(me,target,baseDamage);
	}
}