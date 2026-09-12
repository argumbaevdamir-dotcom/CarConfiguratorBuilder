package kz.sdp.carconfigurator.builder;

import kz.sdp.carconfigurator.model.Car;
import kz.sdp.carconfigurator.model.Transmission;

public interface CarBuilder {
    CarBuilder reset();

    CarBuilder model(String model);

    CarBuilder engineLiters(double engineLiters);

    CarBuilder color(String color);

    CarBuilder seats(int seats);

    CarBuilder transmission(Transmission transmission);

    CarBuilder sunroof(boolean hasSunroof);

    CarBuilder parkingSensors(boolean hasParkingSensors);

    Car build();
}

