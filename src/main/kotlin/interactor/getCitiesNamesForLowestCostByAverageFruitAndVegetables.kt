package interactor

import model.CityEntity

class GetCitiesNamesForLowestCostByAverageFruitAndVegetables(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(limit: Int): List<String>{
        return dataSource
            .getAllCitiesData()
            .filter (::excludeNullAverageFruitAndNullSalaries)
            .sortedBy (::AverageFruitAndVegetablesVsAverageSalaries)
            .take(limit)
            .map { it.cityName }
    }

    private fun excludeNullAverageFruitAndNullSalaries(cityEntity: CityEntity): Boolean {
        return cityEntity.fruitAndVegetablesPrices != null
                && cityEntity.averageMonthlyNetSalaryAfterTax != null
    }

    fun calculateAverageFruitAndVegetables(cityEntity: CityEntity): Float{
        val kilos = cityEntity.fruitAndVegetablesPrices
        val sumList = listOf(kilos.apples1kg, kilos.banana1kg,
                             kilos.oranges1kg, kilos.tomato1kg,
                             kilos.potato1kg, kilos.onion1kg,
                            kilos.lettuceOneHead)

    }

    fun AverageFruitAndVegetablesVsAverageSalaries(cityEntity: CityEntity): Float {
        return calculateAverageFruitAndVegetables(cityEntity) / cityEntity.averageMonthlyNetSalaryAfterTax!!
    }
}

/* this is instead of the previous two functions calculateAverageFruitAndVegetables() and
AverageFruitAndVegetablesVsAverageSalaries()   because there is an error in calculateAverageFruitAndVegetables() it should return something
try to use the following function to solve the error

private fun calculateAverageFruitAndVegetablesVsAverageSalaries(cityEntity: CityEntity): Float {
        val fruitAndVegetablesPrices = cityEntity.fruitAndVegetablesPrices!!
        val totalFruitAndVegetablesPrice = fruitAndVegetablesPrices.run {
            apples1kg!! + banana1kg!! + oranges1kg!! + tomato1kg!! + potato1kg!! + onion1kg!! + lettuceOneHead!!
        }
        val averageFruitAndVegetablesPrice = totalFruitAndVegetablesPrice / 7f
        return averageFruitAndVegetablesPrice / cityEntity.averageMonthlyNetSalaryAfterTax!!
    }
}*/
