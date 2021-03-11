

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */




public class Warrior extends Hero
{

    public Warrior()
	{

		super("Warrior", 250, 4, .8);
		addAction(new CuttingWeapon("Zweihander", 100)); // Barbarian bro goes WHACK
		addAction(new HealingItem("Nordic Ale", 75, 3));
    }//end constructor


}//end Hero class