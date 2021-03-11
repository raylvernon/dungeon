public class Action
{
	public Action(String n)
	{
		name = n;
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
}