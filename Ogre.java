

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Ogre extends Monster
{

    public Ogre()
	{
		super("Oscar the Ogre", 200, 2, .6);
		addAction(new CrushingWeapon("Gnarled Oak Club", 80));
	
    }//end constructor



}//end Monster class