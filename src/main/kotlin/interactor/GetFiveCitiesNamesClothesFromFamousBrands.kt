package interactor

import model.CityEntity
import model.ClothesPrices

class GetFiveCitiesNamesClothesFromFamousBrands(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(limit: Int): List<String>{
        return dataSource
            .getAllCitiesData()
            .filter (::excludeNullClothesPrices)
            .sortedBy (::getFiveCities)
            .take(limit)
            .map { it.cityName }
    }

    fun excludeNullClothesPrices(cityEntity: CityEntity): Boolean{
        return cityEntity.clothesPrices != null
    }

    fun getFiveCities(cityEntity: CityEntity): Float {
        return cityEntity.clothesPrices
    }
}