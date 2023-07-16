package interactor
import model.CityEntity

class GetTop10CountriesNameWithHighTaxesOnCarbonatedDrinks (private val dataSource: CostOfLivingDataSource,
){

    fun execute(limit: Int): List<Pair<String, Float>> {
        val citiesData = dataSource.getAllCitiesData()
            .filter(::excludeCarbonatedDrinksLowQualityData)
            .sortedByDescending {it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }
        return if (limit > 0) {
            citiesData.take(limit)
                .map {Pair(it.country, it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!!)}
        } else {
            throw Exception("Not valid ")
        }
    }

    private fun excludeCarbonatedDrinksLowQualityData(city: CityEntity): Boolean {
        return city.run {
            drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null &&
                    dataQuality &&
                    averageMonthlyNetSalaryAfterTax != null
        }
    }
}