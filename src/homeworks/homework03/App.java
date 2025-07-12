package homeworks.homework03;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Tv tvSamsung = new Tv("Samsung", 65, 60, "Android",
                "Republic of Korea");
        Tv tvYandex = new Tv("Yandex", 55, "China", "YaOS");
        Tv tvHorizont = new Tv(37, "Taiwan");

        System.out.println("Параметры телевизора Samsung: " + tvSamsung);
        System.out.println("Параметры телевизора Yandex: " + tvYandex);
        System.out.println("Параметры телевизора Horizont: " + tvHorizont);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Хотите создать новый телевизор?");
        System.out.println("Enter true or false: ");
        boolean createNewTv = scanner.nextBoolean();
        if (createNewTv) {
            Scanner newTv = new Scanner(System.in);
            System.out.println("Введите Брэнд: ");
            String brand = newTv.next();
            System.out.println("Введите размер экрана: ");
            int screenSize = newTv.nextInt();
            System.out.println("Введите операционную систему устройства: ");
            String os = newTv.next();
            Tv customTv = new Tv(brand, screenSize, os);
            System.out.println("Параметры вашего кастомного телевизора " + brand + ": " + customTv);
        }
    }
}
