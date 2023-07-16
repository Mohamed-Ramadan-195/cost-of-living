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


    //(5)  GetTop5citiesNameThatHasFamousBrandsForClothes
    val famousBrandsForClothes = GetTop5citiesNameThatHasFamousBrandsForClothes(dataSource)
    val topCities = famousBrandsForClothes.execute(5)
    print("The Top 5 Cities with Famous Brands for Clothes:")
    printSeparationLine()
    topCities.forEachIndexed { index, cityName ->
        println("${index + 1}. ${cityName.capitalize()}")

    }
    printSeparationLine()
    //(6) GetCheapestApartment
    println("Enter your Full-Time-Job salary")
    val fullTimeJobSalary = readln().toDouble()
    val cheapestApartments = GetCheapestApartments(dataSource, fullTimeJobSalary).execute(10)
    print("The Top 10 Apartments are...")
    printSeparationLine()
    cheapestApartments.forEachIndexed { index, (cityName, yearsToBuy) ->
        println("${index + 1}. ${cityName.capitalize()} -> $yearsToBuy years")

}
    printSeparationLine()

    //(9)GetTop10CountriesNameWithHighTaxesOnCarbonatedDrinks
    val highTaxesOnCarbonatedDrinks = GetTop10CountriesNameWithHighTaxesOnCarbonatedDrinks(dataSource).execute(10)
    print("The Top 10 Countries With High Taxes On Carbonated Drinks are...")
    printSeparationLine()
    highTaxesOnCarbonatedDrinks.forEachIndexed { index, (country, drinksPrices) ->
        println("${index + 1}. ${country.capitalize()} -> $drinksPrices USD\$")
    }

}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

