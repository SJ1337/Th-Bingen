package FinalProject

/* class for a single car in the simulation
*/
class Car(d: Boolean, h:MutableList<Int>) {
	/* variables
	 */
	private val drive = d
	private var jammed = false
	private val delayed = false
	private val driveInHours = h

	/* getters / setters
	 */
	public fun getDrive(): Boolean {
		return drive
	}	
	
	public fun getJammed(): Boolean {
		return jammed
	}
	
	public fun setJammed(j: Boolean) {
		jammed = j
	}
	public fun getDelayed(): Boolean {
		return delayed
	}

}
