package deliverycounttests;

import deliverycount.DeliveryCalculator;
import deliverycount.DeliveryCalculator.DeliveryLoadLevel;
import io.qameta.allure.Description;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static consts.Consts.ErrorMessages.FRAGILE_MORE_30_KM;
import static deliverycount.DeliveryCalculator.createDeliverCalculator;
import static matchers.ErrorThrown.errorThrown;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Delivery for fragile items")
public class FragileDelivery {

    private static final int MAX_ALLOWED_DESTINATION_KM_FOR_FRAGILE = 30;

    public DeliveryCalculator deliveryCalculator = createDeliverCalculator();

    @ParameterizedTest(name = "Fragile delivery to max allowed destination has correct price")
    @Description("Fragile items delivery to max allowed destination should have correct price.")
    @CsvFileSource(resources = "/fragile-edgedest-data.csv", numLinesToSkip = 1)
    public void shouldHaveCorrectPriceIfMaxDestFragile(@Param("Bulky") boolean bulky,
                                                       @Param("Delivery load level") DeliveryLoadLevel deliveryLoadLevel,
                                                       @Param("Expected price") double expectedPrice) {
        assertThat("Fragile delivery with maximal allowed destination has incorrect price",
                deliveryCalculator.countPrice(MAX_ALLOWED_DESTINATION_KM_FOR_FRAGILE,
                        bulky, true, deliveryLoadLevel),
                is(expectedPrice));
    }

    @ParameterizedTest(name = "Error thrown if fragile delivery more 30 km")
    @Description("Error should be thrown with correct message if calculate fragile item delivery more 30 km.")
    @CsvFileSource(resources = "/fragile-longdest-data.csv", numLinesToSkip = 1)
    public void errorIfFragileWithLongDestination(@Param("Destination (km)") int destinationKm,
                                                  @Param("Bulky") boolean bulky,
                                                  @Param("Delivery load level") DeliveryLoadLevel deliveryLoadLevel) {
        assertThat("No correct error thrown if calculate fragile delivery more 30 km",
                () -> deliveryCalculator.countPrice(destinationKm, bulky, true, deliveryLoadLevel),
                errorThrown(new Error(String.format(FRAGILE_MORE_30_KM.getMessage(), destinationKm ))));
    }
}
