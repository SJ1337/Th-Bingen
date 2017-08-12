package FinalProject

class RoadNetwork (cap: Int, cars: List<Car>) {
	
	private val Capacity: Int = cap
	private val Cars: List<Car> = cars
	
	/* go through the cars list and count those that want to drive
	 */
	fun countDrivingCars(): Int {
		var n = 0
		
		for (car in Cars) {
			if (car.getDrive()) {
				n++
			}
		}
		
		return n
	}
	
	fun simulationStep() {
		val count = countDrivingCars()
		if (count > Capacity) 
			setCarsJammed(true)
		else
			setCarsJammed(false)
	}
	
	
	/* after calculation, set the cars as jammed
 	 * print the jammed / non-jammed cars
 	 */
	fun setCarsJammed(jammed: Boolean) {
		for (car in Cars) {
			car.setJammed(jammed)
		}
	}
	
}
