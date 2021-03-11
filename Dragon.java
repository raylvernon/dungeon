

public class Dragon extends Monster{
	public Dragon() {
		super("Drex the Dragon", 150, 3, .8);
		addAction(new BreathAttack("Searing Flames", 100));
		addAction(new CuttingWeapon("Talons", 80));
		addAction(new CrushingWeapon("Thorny Tail", 80));
	}
}

