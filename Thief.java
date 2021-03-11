

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Thief extends Hero
{

    public Thief()
	{
		super("Thief", 150, 6, .8);
		addAction(new CuttingWeapon("Crooked Dagger", 50));


    }//end constructor
}