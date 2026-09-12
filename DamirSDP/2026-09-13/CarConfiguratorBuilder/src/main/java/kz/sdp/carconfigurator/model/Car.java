package kz.sdp.carconfigurator.model;

import java.util.Objects;

/** Immutable product created by the builders. */
public final class Car {
    private final String model;
    private final double engineLiters;
    private final String color;
    private final int seats;
    private final Transmission transmission;
    private final boolean hasSunroof;
    private final boolean hasParkingSensors;

    public Car(
            String model,
            double engineLiters,
            String color,
            int seats,
            Transmission transmission,
            boolean hasSunroof,
            boolean hasParkingSensors
    ) {
        this.model = Objects.requireNonNull(model, "Model must not be null.");
        this.engineLiters = engineLiters;
        this.color = Objects.requireNonNull(color, "Color must not be null.");
        this.seats = seats;
        this.transmission = Objects.requireNonNull(transmission, "Transmission must not be null.");
        this.hasSunroof = hasSunroof;
        this.hasParkingSensors = hasParkingSensors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engineLiters=" + engineLiters +
                ", color='" + color + '\'' +
                ", seats=" + seats +
                ", transmission=" + transmission +
                ", sunroof=" + hasSunroof +
                ", parkingSensors=" + hasParkingSensors +
                '}';
    }
}
