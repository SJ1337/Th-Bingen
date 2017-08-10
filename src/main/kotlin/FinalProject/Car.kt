package FinalProject

/* class for a single car in the simulation
*/
class Car(d: Boolean) {
	/* variables
	 */
	private val drive = d
	private var jammed: Boolean = false
	
	
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
	
}