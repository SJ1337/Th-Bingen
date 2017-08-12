package FinalProject

/* class for a single car in the simulation
*/
class Car(d: Boolean) {
	/* variables
	 */
	private var drive = d
	private var jammed: Boolean = false
	
	
	/* getters / setters
	 */
	public fun getDrive(): Boolean {
		return drive
	}
	public fun setDrive(): Boolean {
		drive = d
	}	
	
	public fun getJammed(): Boolean {
		return jammed
	}
	
	public fun setJammed(j: Boolean) {
		jammed = j
	}
	
}
