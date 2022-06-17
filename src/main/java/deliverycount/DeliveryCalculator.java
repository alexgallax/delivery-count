package deliverycount;

import lombok.Setter;

@Setter
public class DeliveryCalculator {

    private static final int MIN_PRICE = 400;

    public enum DeliveryLoadLevel {
        VERY_HIGH(1.6),
        HIGH(1.4),
        INCREASED(1.2),
        STANDARD(1);

        private final double coef;

        DeliveryLoadLevel(double coef) {
            this.coef = coef;
        }
    }

    private DeliveryCalculator() {}

    public static DeliveryCalculator createDeliverCalculator() {
        return new DeliveryCalculator();
    }

    public double countPrice(int destinationKm, boolean bulky, boolean fragile, DeliveryLoadLevel deliveryLoadLevel) {
        return countPrice(destinationKm, bulky, fragile, deliveryLoadLevel.coef);
    }

    public double countPrice(int destinationKm, boolean bulky, boolean fragile, double deliveryLoadLevelCoef) {
        if (destinationKm <= 0) {
            throw new Error(String.format("Delivery destination can't be less or equal zero (%s was requested)",
                    destinationKm));
        }
        if (fragile && destinationKm > 30) {
            throw new Error(String.format("Fragile cargo can't be delivered more than 30 km (%s was requested)",
                    destinationKm));
        }
        return Math.max(MIN_PRICE, (
                (MIN_PRICE
                        + destinationMod(destinationKm)
                        + bulkyMod(bulky)
                        + fragileMod(fragile)
                ) * deliveryLoadLevelCoef
        ));
    }

    private int destinationMod(int destinationKm) {
        if (destinationKm > 30) {
            return 300;
        } else if (destinationKm > 10) {
            return 200;
        } else if (destinationKm > 2) {
            return 100;
        } else {
            return 50;
        }
    }

    private int bulkyMod(boolean bulky) {
        return bulky ? 200 : 100;
    }

    private int fragileMod(boolean fragile) {
        return fragile ? 300 : 0;
    }
}
