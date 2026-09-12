package kz.sdp.carconfigurator.builder;

/** Builder for two-seat sport coupes. */
public final class SportCarBuilder extends BaseCarBuilder {
    private static final double MIN_SPORT_ENGINE_LITERS = 2.0;
    private static final int SPORT_COUPE_SEATS = 2;

    @Override
    protected void validateBuilderRules() {
        if (engineLiters() < MIN_SPORT_ENGINE_LITERS) {
            throw new IllegalStateException("A sport car engine must be at least " + MIN_SPORT_ENGINE_LITERS + " liters.");
        }
        if (seats() != SPORT_COUPE_SEATS) {
            throw new IllegalStateException("A sport coupe must have exactly " + SPORT_COUPE_SEATS + " seats.");
        }
    }
}

