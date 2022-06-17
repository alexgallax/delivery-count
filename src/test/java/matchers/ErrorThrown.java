package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.function.Supplier;

public class ErrorThrown extends TypeSafeMatcher<Supplier<Double>> {

    private final Error expectedError;
    private Error actualError;

    private ErrorThrown(Error expectedError) {
        this.expectedError = expectedError;
    }

    public static Matcher<Supplier<Double>> errorThrown(Error expectedError) {
        return new ErrorThrown(expectedError);
    }

    @Override
    protected boolean matchesSafely(Supplier<Double> error) {
        try {
            error.get();
        } catch (Error err) {
            actualError = err;
            return err.getMessage().equals(expectedError.getMessage());
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expectedError.getMessage()).appendText(" but was ")
                .appendValue(actualError.getMessage());
    }
}
