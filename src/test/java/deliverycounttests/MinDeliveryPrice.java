package deliverycounttests;

import deliverycount.DeliveryCalculator;
import io.qameta.allure.Description;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static consts.Consts.MIN_DELIVERY_PRICE;
import static deliverycount.DeliveryCalculator.createDeliverCalculator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@DisplayName("Minimal delivery price")
public class MinDeliveryPrice {

    private static final double LOAD_LEVEL_TO_GET_PRICE_LESS_THAN_MIN = 0.5;

    public DeliveryCalculator deliveryCalculator = createDeliverCalculator();

    @ParameterizedTest(name = "Delivery has fixed minimal price if calculated less minimal value")
    @Description("Delivery should have fixed minimal price if was calculated less this minimal value." +
            " Passing delivery load level decreasing coefficient if used for testing.")
    @CsvFileSource(resources = "/min-price-data.csv", numLinesToSkip = 1)
    public void shouldHaveMinPriceValueIfCalculatedLess(@Param("Destination (km)") int destinationKm,
                                                        @Param("Bulky") boolean bulky,
                                                        @Param("Fragile") boolean fragile) {
        assertThat("Delivery price is less or equal minimal value",
                deliveryCalculator.countPrice(destinationKm, bulky, fragile, LOAD_LEVEL_TO_GET_PRICE_LESS_THAN_MIN),
                greaterThanOrEqualTo(MIN_DELIVERY_PRICE));
    }
}
