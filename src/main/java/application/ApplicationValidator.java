package application;

import java.util.Objects;

public class ApplicationValidator {

    public static void validate(DimensionDTO dimensions) {
        if (Objects.isNull(dimensions)) {
            throw new CarteNeedsDimensions();
        }
    }
}
