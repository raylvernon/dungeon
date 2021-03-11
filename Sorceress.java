

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */



public class Sorceress extends Hero
{

    public Sorceress()
	{
		super("Sorceress", 150, 5, .7);
		addAction(new RangedWeapon("Fireball", 100));

    }//end constructor

}//end class