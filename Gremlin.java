

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Gremlin extends Monster
{

    public Gremlin()
	{
		super("Gnarltooth the Gremlin", 70, 5, .8);
		addAction(new CuttingWeapon("Rusty Knife", 40));
		addAction(new RangedWeapon("Blow darts", 20));
    }//end constructor
}//end class Gremlin