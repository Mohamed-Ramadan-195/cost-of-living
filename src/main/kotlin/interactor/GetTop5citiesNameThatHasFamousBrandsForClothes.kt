package interactor

import model.CityEntity

class GetTop5citiesNameThatHasFamousBrandsForClothes(private val dataSource: CostOfLivingDataSource,
) {
    fun execute(limit: Int): List<String> {
        return dataSource.getAllCitiesData()
            .filter(excludeClothesLowQualityData)
            .sortedBy(calculateSumOfClothesPrices)
            .take(limit)
            .map { it.cityName }
            .takeIf { it.isNotEmpty() }
            ?: throw Exception("Not Valid")
    }

    private val calculateSumOfClothesPrices = { city: CityEntity ->
        city.clothesPrices.run {
            onePairOfMenLeatherBusinessShoes !! + onePairOfJeansLevis50oneOrSimilar !! + onePairOfNikeRunningShoesMidRange!!+ oneSummerDressInAChainStoreZaraHAndM!!
        }
    }

    private val excludeClothesLowQualityData = { city: CityEntity ->
        city.clothesPrices.run {
            onePairOfJeansLevis50oneOrSimilar != null &&
                    onePairOfMenLeatherBusinessShoes != null &&
                    onePairOfNikeRunningShoesMidRange != null &&
                    oneSummerDressInAChainStoreZaraHAndM != null
        }
    }

}
