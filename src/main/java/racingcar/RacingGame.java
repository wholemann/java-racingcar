package racingcar;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingGame {

    private int numberOfCars;
    private int tryCount;

    private static Random randomGenerator;

    private List<Car> cars;

    public RacingGame(Random randomGenerator, InputData inputData) {
        this.randomGenerator = randomGenerator;
        InputData gameOptions = new InputData(inputData.getNumberOfCars(), inputData.getTryCount());
        numberOfCars = gameOptions.getNumberOfCars();
        tryCount = gameOptions.getTryCount();
        generateCars();
    }

    public void progress() {
        cars.forEach(car -> car.move());
        tryCount -= 1;
    }

    public boolean isFinish() {
        return tryCount == 0;
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    private void generateCars() {
        cars = IntStream
                .range(0, numberOfCars)
                .mapToObj(__ -> new Car(randomGenerator))
                .collect(Collectors.toList());
    }
}
