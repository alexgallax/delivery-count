package deliverycounttests;

import deliverycount.DeliveryCalculator;
import deliverycount.DeliveryCalculator.DeliveryLoadLevel;
import io.qameta.allure.Description;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static consts.Consts.ErrorMessages.INVALID_DESTINATION;
import static deliverycount.DeliveryCalculator.createDeliverCalculator;
import static matchers.ErrorThrown.errorThrown;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Invalid arguments for delivery price calculation")
public class InvalidInputDeliveryPrice {

    public DeliveryCalculator deliveryCalculator = createDeliverCalculator();

    @ParameterizedTest(name = "Error thrown if invalid destination, all other arguments valid")
    @Description("Error should be thrown with correct message if delivery destination has invalid value." +
            " All other arguments are valid.")
    @CsvFileSource(resources = "/invalid-dest-data.csv", numLinesToSkip = 1)
    public void errorIfInvalidDestination(@Param("Destination (km)") int destinationKm,
                                          @Param("Bulky") boolean bulky,
                                          @Param("Fragile") boolean fragile,
                                          @Param("Delivery load level") DeliveryLoadLevel deliveryLoadLevel) {
        assertThat("No correct error thrown if invalid delivery destination",
                () -> deliveryCalculator.countPrice(destinationKm, bulky, fragile, deliveryLoadLevel),
                errorThrown(new Error(String.format(INVALID_DESTINATION.getMessage(), destinationKm))));
    }
}
