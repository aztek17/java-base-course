package homeworks.homework05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /*
        Test data: Первый - Поле чудес; СТС - Галилео; Евроспорт - Футбол. Полуфинал Лиги Чемпионов; Россия 1 - Вести; Матч ТВ - Автоспорт. Формула 1; Карусель - Мультфильм. Холодное сердце
         */
        Channel[] channels = parseChannels();
        List<Channel> channelsOfFirstTv = new ArrayList<>();
        List<Channel> channelsOfSecondTv = new ArrayList<>();
        for (int i = 0; i < channels.length; i++) {
            if (i % 2 == 0) {
                channelsOfFirstTv.add(channels[i]);
            } else {
                channelsOfSecondTv.add(channels[i]);
            }
        }

        Tv firstTv = new Tv("Toshiba", 77, channelsOfFirstTv);
        Tv secondTv = new Tv("Samsung", 90, channelsOfSecondTv);
        System.out.println();

        firstTv.pressPowerButton(true);
        firstTv.pressPowerButton(false);
        System.out.println();
        secondTv.pressPowerButton(true);

        Scanner scanner = new Scanner(System.in);
        while (secondTv.isPoweredOn()) {
            System.out.println("\n*** Введите номер канала или END для выключения телевизора ***. \n*** Доступные каналы для переключения: ***" + secondTv.showAvailableChannels());
            String inputValue = scanner.next();
            if (inputValue.equals("END")) {
                secondTv.pressPowerButton(false);
                break;
            } else {
                secondTv.changeCurrentChannel(Integer.parseInt(inputValue));
            }
        }

    }

    private static Channel[] parseChannels() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ведите список названий каналов и телепередач для настройки телевизоров");
        String[] tvGuide = scanner.nextLine().split(";");

        Channel[] channels = new Channel[tvGuide.length];
        for (int i = 0; i < tvGuide.length; i++) {
            channels[i] = new Channel(tvGuide[i].split("-")[0].trim(), tvGuide[i].split("-")[1].trim());
        }
        return channels;
    }
}

