package tiles;




	/*
	 *  overview:			rep		AICanHit	use
	 *  	Water 		= 	0		true		empty tile
	 * 		Illogical	= 	1		false		for AI logic
	 * 		Hull 		=	2		false		ship part
	 * 		Hit 		=	3		false		broken ship part
	 * 		Miss 		=	4		false		a shot that landed in water
	 */


public abstract class AbstractTile {
	protected int rep; //used to know what type of representation item(?) to show
	protected boolean AICanHit; //used in upper matrix for AI to know if it should shoot there.
	
	public AbstractTile() {
		rep = 0;
		AICanHit = false;
	}
	
	
	public abstract boolean hit();


	public int getRep() {
		return rep;
	}
	
	public boolean AICanHit() {
		return AICanHit;
	}
	
}
