public class SwordStrike extends Action
{
	int baseDamage;
	
    public SwordStrike(int baseDamage)
	{
		super("Sword Strike");
		this.baseDamage = baseDamage;
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " Slashes with their sword at " + target.getName() );
		target.subtractHitPoints(baseDamage);
	}
}