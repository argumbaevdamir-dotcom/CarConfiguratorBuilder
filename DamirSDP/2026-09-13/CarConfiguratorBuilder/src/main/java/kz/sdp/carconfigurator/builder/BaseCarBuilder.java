package kz.sdp.carconfigurator.builder;

import kz.sdp.carconfigurator.model.Car;
import kz.sdp.carconfigurator.model.Transmission;

import java.util.Objects;

/** Holds fluent configuration steps shared by all car representations. */
public abstract class BaseCarBuilder implements CarBuilder {
    private String model;
    private double engineLiters;
    private String color;
    private int seats;
    private Transmission transmission;
    private boolean hasSunroof;
    private boolean hasParkingSensors;

    @Override
    public BaseCarBuilder reset() {
        model = null;
        engineLiters = 0;
        color = null;
        seats = 0;
        transmission = null;
        hasSunroof = false;
        hasParkingSensors = false;
        return this;
    }

    @Override
    public BaseCarBuilder model(String model) {
        this.model = model;
        return this;
    }

    @Override
    public BaseCarBuilder engineLiters(double engineLiters) {
        this.engineLiters = engineLiters;
        return this;
    }

    @Override
    public BaseCarBuilder color(String color) {
        this.color = color;
        return this;
    }

    @Override
    public BaseCarBuilder seats(int seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public BaseCarBuilder transmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    @Override
    public BaseCarBuilder sunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
        return this;
    }

    @Override
    public BaseCarBuilder parkingSensors(boolean hasParkingSensors) {
        this.hasParkingSensors = hasParkingSensors;
        return this;
    }

    @Override
    public Car build() {
        validateCommonFields();
        validateBuilderRules();
        return new Car(model, engineLiters, color, seats, transmission, hasSunroof, hasParkingSensors);
    }

    protected abstract void validateBuilderRules();

    protected final double engineLiters() {
        return engineLiters;
    }

    protected final int seats() {
        return seats;
    }

    private void validateCommonFields() {
        Objects.requireNonNull(model, "Model is required.");
        Objects.requireNonNull(color, "Color is required.");
        Objects.requireNonNull(transmission, "Transmission is required.");

        if (engineLiters <= 0) {
            throw new IllegalStateException("Engine volume must be positive.");
        }
        if (seats < 1) {
            throw new IllegalStateException("A car must have at least one seat.");
        }
    }
}

