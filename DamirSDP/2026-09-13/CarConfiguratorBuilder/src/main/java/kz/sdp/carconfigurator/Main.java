package kz.sdp.carconfigurator;

import kz.sdp.carconfigurator.builder.CityCarBuilder;
import kz.sdp.carconfigurator.builder.SportCarBuilder;
import kz.sdp.carconfigurator.director.CarDirector;
import kz.sdp.carconfigurator.model.Car;
import kz.sdp.carconfigurator.model.Transmission;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        CarDirector director = new CarDirector();

        Car dailyCar = director.createDailyCommuter(new CityCarBuilder());
        Car weekendCar = director.createWeekendSportsCar(new SportCarBuilder());
        Car customCoupe = new SportCarBuilder()
                .reset()
                .model("Nova RS")
                .engineLiters(2.5)
                .color("Graphite")
                .seats(2)
                .transmission(Transmission.AUTOMATIC)
                .sunroof(false)
                .parkingSensors(true)
                .build();

        printCar("Director recipe: daily city car", dailyCar);
        printCar("Director recipe: weekend sports car", weekendCar);
        printCar("Custom fluent configuration", customCoupe);
    }

    private static void printCar(String label, Car car) {
        System.out.println(label);
        System.out.println(car);
        System.out.println();
    }
}
