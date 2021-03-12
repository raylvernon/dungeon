public class BreathAttack extends Action
{
	private int baseDamage;
	public BreathAttack(String name, int baseDamage)
	{
		super(name);
		this.baseDamage = baseDamage;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " breathes " + getName() + " at " + target.getName());
		attack(me,target,baseDamage);
	}
}