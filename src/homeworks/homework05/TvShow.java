package homeworks.homework05;

import java.util.Objects;
import java.util.Random;

public class TvShow {
    private String showName;
    private float rating;
    private int countViewer;

    public TvShow(String showName) {
        setShowName(showName);
        setRating(generateRandomNumber(10));
        setCountViewer(generateRandomNumber(30000));
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCountViewer(int countViewer) {
        this.countViewer = countViewer;
    }

    private int generateRandomNumber(int max) {
        int min = 1;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TvShow tvShow = (TvShow) o;
        return Float.compare(rating, tvShow.rating) == 0 && countViewer == tvShow.countViewer && Objects.equals(showName, tvShow.showName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(showName);
        result = 31 * result + Float.hashCode(rating);
        result = 31 * result + countViewer;
        return result;
    }

    @Override
    public String toString() {
        return "\nНазвание телепередачи - " + showName;
    }
}
