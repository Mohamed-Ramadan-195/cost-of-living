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

    fun excludeNullAverageFruitAndNullSalaries(cityEntity: CityEntity): Boolean {
        return cityEntity.fruitAndVegetablesPrices != null
                && cityEntity.averageMonthlyNetSalaryAfterTax != null
    }

    fun calculateAverageFruitAndVegetables(cityEntity: CityEntity): Float{
        val kilos = cityEntity.fruitAndVegetablesPrices!!
        val totalFruitAndVegetablesPrices = kilos.run {
            apples1kg!! + banana1kg!! + oranges1kg!! + tomato1kg!! + potato1kg!! + onion1kg!! + lettuceOneHead!!
        }
        return totalFruitAndVegetablesPrices / NUMBER_OF_VARIABLES
    }

    fun AverageFruitAndVegetablesVsAverageSalaries(cityEntity: CityEntity): Float {
        return calculateAverageFruitAndVegetables(cityEntity) / cityEntity.averageMonthlyNetSalaryAfterTax!!
    }

    companion object {
        const val NUMBER_OF_VARIABLES = 7
    }
}