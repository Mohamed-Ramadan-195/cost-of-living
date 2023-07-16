package interactor
import model.CityEntity

class GetCheapestApartments(
    private val dataSource: CostOfLivingDataSource,
    private val fullTimeJobSalary: Double,
) {
    fun execute(limit: Int): List<Pair<String, Double>> {
        val citiesData = dataSource
            .getAllCitiesData()
            .filter(::excludeLowQualityData)
            .sortedByDescending { getNumberOfYearsToBuyApartment(it) }
        return if (limit > 0) {
            citiesData.take(limit)
                .map { city -> city.cityName to getNumberOfYearsToBuyApartment(city) }
        } else {
            throw Exception("Not valid ")
        }
    }

    private fun excludeLowQualityData(city: CityEntity): Boolean {
        return city.run {
            realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre != null &&
                    dataQuality &&
                    averageMonthlyNetSalaryAfterTax != null
        }
    }
    private fun getNumberOfYearsToBuyApartment(city: CityEntity): Double {
        val apartmentCost = city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!! * 100
        val yearlySavings = fullTimeJobSalary - city.averageMonthlyNetSalaryAfterTax!!
        return apartmentCost / yearlySavings
    }
}