package consts;

public class Consts {

    public static final double MIN_DELIVERY_PRICE = 400;

    public enum ErrorMessages {
        FRAGILE_MORE_30_KM("Fragile cargo can't be delivered more than 30 km (%s was requested)"),
        INVALID_DESTINATION("Delivery destination can't be less or equal zero (%s was requested)");

        private final String message;

        ErrorMessages(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
