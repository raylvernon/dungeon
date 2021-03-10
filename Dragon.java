
public class Dragon extends Monster{
	public Dragon() {
		super("Drex the Dragon", 150, 3, .8, .4, 30, 50, 30, 50);
	}
	
	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " breaths fire at " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack

}

