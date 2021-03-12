public class Mimicry extends Action
{
	public Mimicry()
	{
		super("Mimicry");
	}
	
	public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " seems to turn inside out! With horror, " + target.getName() + " finds themselves staring at their own reflection!");
		
		for(Action a: target.getActions())
			me.addAction(a);
		
		me.setAttackSpeed(4);
		me.removeAction(this);
	}
}