package FinalProject

import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings

/* the scenario function creates a dozen cars, puts them into a road network,
 * simulates the traffic and prints the jammed / non-jammed for every car.
 */
fun scenario(cars: MutableList<Car>) {

	val rn = RoadNetwork(7, cars)



	rn.simulationStep()

}
fun scenarioCSV() {
	val cars: MutableList<Car> = parseCSV()

	scenario(cars)

	printResultsToCSV(cars)

}
fun scenarioStatic(){
	val car1 = Car(true, h = mutableListOf(1,2,3))
	val car2 = Car(false,h = mutableListOf(0))
	val car3 = Car(true,h = mutableListOf(4,5,6,7))
	val car4 = Car(true,h = mutableListOf(6,7,8))
	val car5 = Car(false,h = mutableListOf(0))
	val car6 = Car(true,h = mutableListOf(10,11,12))
	val car7 = Car(true,h = mutableListOf(23,24))
	val car8 = Car(false,h = mutableListOf(0))
	val car9 = Car(true,h = mutableListOf(1,2,3,4))
	val car10 = Car(true,h = mutableListOf(2,3))
	val car11 = Car(false,h = mutableListOf(0))
	val car12 = Car(true,h = mutableListOf(5,6,10))

	val cars = mutableListOf(car1, car2, car3, car4, car5, car6, car7, car8, car8, car9, car10, car11, car12)
	scenario(cars)


		for (car in cars) {

			println("${car.getJammed()}, ${car.getDelayed()}")
		}

}
fun main(args: Array<String>) {

	scenarioCSV()

	scenarioStatic()



}


fun parseCSV(): MutableList<Car> {
	val carListCSV: MutableList<Car> = mutableListOf()

	val settings = CsvParserSettings()
	settings.format.setLineSeparator("\n")
	settings.isHeaderExtractionEnabled = true

	val csvParser = CsvParser(settings)
	val reader = FileAccess().getReader("/traffic.csv")
	val allRows: MutableList<Record> = csvParser.parseAllRecords(reader)


	for (record in allRows){


		val wantDriveStr : String = record.values[1]
		val wantDrive : Boolean = wantDriveStr.toBoolean()


		val driveInHoursStr : String = record.values[2]
		val driveInHoursArray: List<String> = driveInHoursStr.split(";")

		val driveInHoursList: MutableList<Int> = mutableListOf()

		for (hour in driveInHoursArray) {
			val hourInt: Int = hour.toInt()
			driveInHoursList.add(hourInt)
		}

		carListCSV.add(Car(d = wantDrive, h = driveInHoursList))

	}
	return carListCSV
}

fun printResultsToCSV(results: List<Car>, outputFile: String = "results.csv") {
	val writer = FileAccess().getWriter(outputFile)

	val csvWriter = CsvWriter(writer, CsvWriterSettings())



	// Write the record headers of this file
	val vehicleRows: MutableList<Array<Any>> = mutableListOf()

	var drive = "I want to drive"
	var jammed = "In a traffic jam"
	var delayed = "Delayed"
	var row: Array<Any> = arrayOf(drive, jammed, delayed)
	vehicleRows.add(row)

	for (result in results) {

		drive = result.getDrive().toString()
		jammed = result.getJammed().toString()
		delayed = result.getDelayed().toString()
		row = arrayOf(drive, jammed, delayed)
		vehicleRows.add(row)
	}

	csvWriter.writeRowsAndClose(vehicleRows)
}
