package FinalProject

import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings

/* the scenario function creates a dozen cars, puts them into a road network,
 * simulates the traffic and prints the jammed / non-jammed for every car.
 */
fun scenario(Cars: MutableList<Car>) {
	/*val car1 = Car(true)
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
	val car12 = Car(true) */
	
	//val Cars = listOf(car1, car2, car3, car4, car5, car6, car7, car8, car8, car9, car10, car11, car12)
	val rn = RoadNetwork(7, Cars)
	rn.simulationStep()
	rn.printCarsJammed()
}

fun main(args: Array<String>) {
	val Cars : MutableList<Car> = parseCSV()

	scenario(Cars)

	printResultsToCSV(Cars)



}


fun parseCSV(): MutableList<Car> {
	val CarListCSV: MutableList<Car> = mutableListOf()

	val settings = CsvParserSettings()
	settings.format.setLineSeparator("\n")
	settings.isHeaderExtractionEnabled = true

	val csvParser = CsvParser(settings)
	val reader = FileAccess().getReader("/traffic.csv")
	val allRows: MutableList<Record> = csvParser.parseAllRecords(reader)


	for (record in allRows){
		val CarIdStr : String = record.values[0]
		val CarId : Int = CarIdStr.toInt()

		val WantDriveStr : String = record.values[1]
		val WantDrive : Boolean = WantDriveStr.toBoolean()

		CarListCSV.add(Car(d = WantDrive))

	}
	return CarListCSV
}

fun printResultsToCSV(results: List<Car>, outputFile: String = "results.csv") {
	val writer = FileAccess().getWriter(outputFile)

	val csvWriter = CsvWriter(writer, CsvWriterSettings())



	// Write the record headers of this file
	val vehicleRows: MutableList<Array<Any>> = mutableListOf()

	var Drive = "I want to drive"
	var Jammed = "In a traffic jam"
	var row: Array<Any> = arrayOf(Drive, Jammed)
	vehicleRows.add(row)

	for (result in results) {

		Drive = result.getDrive().toString()
		Jammed = result.getJammed().toString()
		row = arrayOf(Drive, Jammed)
		vehicleRows.add(row)
	}

	csvWriter.writeRowsAndClose(vehicleRows)
}