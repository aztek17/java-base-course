package homeworks.homework11Additional;

import java.util.List;

public interface CarRepository {

    List<Car> findAll();

    void save(String text);
}
