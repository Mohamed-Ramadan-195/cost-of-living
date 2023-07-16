package interactor

import model.CityEntity

class GetCityHasCheapestInternetConnectionInteractor(
    private val dataSource: CostOfLivingDataSource,

) {

    fun execute(limit: Int): CityEntity? {
        return dataSource
            .getAllCitiesData()
            .filter (::excludeNullSalariesAndNullInternetPrice)
            .take(limit)
            .minByOrNull (::InternetPriceVsAverageSalary)
    }

    private fun excludeNullSalariesAndNullInternetPrice(cityEntity: CityEntity): Boolean {
        return  cityEntity.averageMonthlyNetSalaryAfterTax != null
                && cityEntity.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != null
    }

    private fun InternetPriceVsAverageSalary(cityEntity: CityEntity): Float {
        return (cityEntity.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl?.div(cityEntity.averageMonthlyNetSalaryAfterTax!!))!! * 100
    }
}