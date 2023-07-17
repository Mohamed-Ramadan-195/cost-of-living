import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCitiesNamesForLowestCostByAverageFruitAndVegetablesInteractor
import interactor.GetHighestSalaryAverageCititesNamesInteractor

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)

    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 10))
    printSeparationLine()

//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute())

    val getCitiesNamesForLowestCostByAverageFruitAndVegetablesInteractor = GetCitiesNamesForLowestCostByAverageFruitAndVegetablesInteractor(dataSource)
    println(getCitiesNamesForLowestCostByAverageFruitAndVegetablesInteractor.execute(10))
}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

