package deliverycounttests;

import deliverycount.DeliveryCalculator;
import deliverycount.DeliveryCalculator.DeliveryLoadLevel;
import io.qameta.allure.Description;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static consts.Consts.MIN_DELIVERY_PRICE;
import static deliverycount.DeliveryCalculator.createDeliverCalculator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@DisplayName("Delivery price calculation")
public class ValidInputDeliveryPrice {

    public DeliveryCalculator deliveryCalculator = createDeliverCalculator();

    @ParameterizedTest(name = "Delivery with destination={0} km, bulky={1}, fragile={2}," +
            " delivery load level={3} has correct price={4}")
    @Description("Delivery with a valid combination of all input arguments (destination, bulky," +
            " fragile, delivery load level) should have correct price.")
    @CsvFileSource(resources = "/valid_args-data.csv", numLinesToSkip = 1)
    public void shouldHaveCorrectPriceIfValidInput(@Param("Destination (km)") int destinationKm,
                                                   @Param("Bulky") boolean bulky,
                                                   @Param("Fragile") boolean fragile,
                                                   @Param("Delivery load level") DeliveryLoadLevel deliveryLoadLevel,
                                                   @Param("Expected price") double expectedPrice) {
        double price = deliveryCalculator.countPrice(destinationKm, bulky, fragile, deliveryLoadLevel);

        assertThat("Delivery has incorrect price", price, is(expectedPrice));
        assertThat("Delivery price is less than minimal price", price, greaterThanOrEqualTo(MIN_DELIVERY_PRICE));
    }
}
