import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.*

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)
//    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
//    println(getHighestSalaryAverageCities.execute(limit = 10))
//    printSeparationLine()
//
//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute(10))

    //GetCheapestApartment
    println("Enter your Full-Time-Job salary")
    val fullTimeJobSalary = readln().toDouble()
    val cheapestApartment = GetCheapestApartments(dataSource, fullTimeJobSalary).execute(10)
    print("The Top 10 Apartments are... : ")
    printSeparationLine()
    cheapestApartment.forEach { (cityName, yearsToBuy) ->
        println("${cityName.capitalize()} ->  $yearsToBuy years ")
    }
    printSeparationLine()

    //GetTop10CountriesNameWithHighTaxesOnCarbonatedDrinks
    val highTaxesOnCarbonatedDrinks = GetTop10CountriesNameWithHighTaxesOnCarbonatedDrinks(dataSource,).execute(10)
    print("The Top 10 Countries With High Taxes On Carbonated Drinks are... : ")
    printSeparationLine()
    highTaxesOnCarbonatedDrinks
        .forEach { (country, drinksPrices) ->
            println("${country.capitalize()} ->  $drinksPrices USD\$ ")
        }
    printSeparationLine()

}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

