package homeworks.homework05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Tv firstTv = new Tv("Vityaz", 55, 89, true);
        Tv secondTv = new Tv("Sokol", 3, 55, true);
        Tv thirdTv = new Tv("Horizont", 39, 69, false);
        Tv fourthTv = new Tv("Samsung", 7, 49, true);
        Tv fifthTv = new Tv("Yandex", 129, 58, true);
        Tv sixthTv = new Tv("LG", 300, 22, false);
        Tv seventhTv = new Tv("Philips", 3, 60, true);
        Tv eighthTv = new Tv("Toshiba", 18, 33, false);
        Tv ninthTv = new Tv("Hisense", 26, 77, true);
        Tv tenthTv = new Tv("Haier", 501, 54, false);
        Tv[] tvs = {firstTv, secondTv, thirdTv, fourthTv, fifthTv, sixthTv, seventhTv, eighthTv, ninthTv, tenthTv};

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите максимальное допустимое значение звука: ");
        int maxVolume = scanner.nextInt();

        Arrays.sort(tvs, Comparator.comparing(Tv::getCurrentChannel));

        for (Tv tv : tvs) {
            if (tv.isPoweredOn() & tv.getVolume() >= maxVolume) {
                System.out.println(tv);
            }
        }
    }
}

