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
        val kilos = cityEntity.fruitAndVegetablesPrices
        val sumList = listOf(kilos.apples1kg, kilos.banana1kg,
                             kilos.oranges1kg, kilos.tomato1kg,
                             kilos.potato1kg, kilos.onion1kg,
                            kilos.lettuceOneHead)
        val total = sumList
    }

    fun AverageFruitAndVegetablesVsAverageSalaries(cityEntity: CityEntity): Float {
        return calculateAverageFruitAndVegetables(cityEntity) / cityEntity.averageMonthlyNetSalaryAfterTax!!
    }
}