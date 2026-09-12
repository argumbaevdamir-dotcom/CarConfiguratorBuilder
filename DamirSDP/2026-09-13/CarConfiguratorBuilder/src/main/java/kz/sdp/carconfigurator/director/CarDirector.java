package kz.sdp.carconfigurator.director;

import kz.sdp.carconfigurator.builder.CarBuilder;
import kz.sdp.carconfigurator.model.Car;
import kz.sdp.carconfigurator.model.Transmission;

/** Stores reusable build recipes while builders create their own representations. */
public final class CarDirector {
    public Car createDailyCommuter(CarBuilder builder) {
        return builder.reset()
                .model("Astra City")
                .engineLiters(1.6)
                .color("Silver")
                .seats(5)
                .transmission(Transmission.AUTOMATIC)
                .sunroof(false)
                .parkingSensors(true)
                .build();
    }

    public Car createWeekendSportsCar(CarBuilder builder) {
        return builder.reset()
                .model("Orion GT")
                .engineLiters(3.0)
                .color("Racing Red")
                .seats(2)
                .transmission(Transmission.MANUAL)
                .sunroof(true)
                .parkingSensors(true)
                .build();
    }
}

