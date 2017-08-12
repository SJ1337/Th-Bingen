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
	val car1 = Car(true)
	val car2 = Car(false)
	val car3 = Car(true)
	val car4 = Car(true)
	val car5 = Car(false)
	val car6 = Car(true)
	val car7 = Car(true)
	val car8 = Car(false)
	val car9 = Car(true)
	val car10 = Car(true)
	val car11 = Car(false)
	val car12 = Car(true)

	val cars = mutableListOf(car1, car2, car3, car4, car5, car6, car7, car8, car8, car9, car10, car11, car12)
	scenario(cars)


		for (car in cars) {
			println(car.getJammed())
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

		carListCSV.add(Car(d = wantDrive))

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
	var row: Array<Any> = arrayOf(drive, jammed)
	vehicleRows.add(row)

	for (result in results) {

		drive = result.getDrive().toString()
		jammed = result.getJammed().toString()
		row = arrayOf(drive, jammed)
		vehicleRows.add(row)
	}

	csvWriter.writeRowsAndClose(vehicleRows)
}
