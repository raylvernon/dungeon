public class Action
{
	public Action(String n)
	{
		name = n;
	}
	
    public void perform(DungeonCharacter me, DungeonCharacter target)
	{
		System.out.println(me.getName() + " points his finger accusingly at " + target.getName());
	}

	protected String name;
	
	public String getName()
	{
		return name;
	}
}