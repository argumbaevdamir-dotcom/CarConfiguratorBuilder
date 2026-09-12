package kz.sdp.carconfigurator.builder;

/** Builder for practical city cars. */
public final class CityCarBuilder extends BaseCarBuilder {
    private static final double MAX_CITY_ENGINE_LITERS = 2.0;
    private static final int MIN_CITY_SEATS = 4;

    @Override
    protected void validateBuilderRules() {
        if (engineLiters() > MAX_CITY_ENGINE_LITERS) {
            throw new IllegalStateException("A city car engine cannot exceed " + MAX_CITY_ENGINE_LITERS + " liters.");
        }
        if (seats() < MIN_CITY_SEATS) {
            throw new IllegalStateException("A city car needs at least " + MIN_CITY_SEATS + " seats.");
        }
    }
}

