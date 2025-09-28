import model.User;
import repositories.UserRepository;
import repositories.impl.UserRepositoryFileImpl;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserRepository repository = new UserRepositoryFileImpl();
        repository.deleteAll();

        // Создаем пользователей
        repository.create(new User(
                "f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true"));
        repository.create(new User(
                "7a7323a4-2a2c-45d1-862c-64c3fc8dbbd2|2024-12-25T20:10:11.556|puma1_95|83267dsdg|83267dsdg|Сыров|Сергей|Николаевич|44|true"));
        repository.create(new User(
                "4f46861d-54f9-4c5f-a6c0-7336d854dc71|2020-12-25T21:10:11.556|strizh_71|ksdhjf123|ksdhjf123|Стрижов|Алексей|Семенович|30|true"));

        // Поиск пользователя по ID
        User user = repository.findById("4f46861d-54f9-4c5f-a6c0-7336d854dc71");
        System.out.println("Поиск пользователя по ID: " + user);

        // Выгрузка всех пользователей
        List<User> allUsers = repository.findAll();
        System.out.println("\nВыгрузка всего списка пользователей: " + allUsers);

        User patchData = new User("f5a8a3cb-4ac9-4b3b-8a65-c425e129b9d2|2001-12-25T19:10:11.556|new_login_2|789ghs|789ghs|Хвостов|Виктор|Павлович|30|false");
        System.out.println("\nОбновление пользователя ДО: " + repository.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));
        repository.update(patchData);
        System.out.println("Обновление пользоватея ПОСЛЕ: " + repository.findById("f5a8a3cb-4ac9-4b3b-8a65-c425e129b9d2"));

        System.out.println("\nУдаление пользователя по ID. Список изначально: \n" + repository.findAll());
        repository.deleteById("7a7323a4-2a2c-45d1-862c-64c3fc8dbbd2");
        System.out.println("Удаление пользователя по ID. Список после удаления: \n" + repository.findAll());

        System.out.println("\nУдаление всех пользователей. Список изначально: \n" + repository.findAll());
        repository.deleteAll();
        System.out.println("Удаление всех пользователей. Список после удаления: \n" + repository.findAll());

        repository.create(new User(
                "a4dfdab4-2e00-4853-b787-798207cb1784|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|30|true"));
        repository.create(new User(
                "73bd915f-ffbf-46a9-b1e6-cdfc38d2bdfc|2024-12-25T20:10:11.556|puma1_95|83267dsdg|83267dsdg|Сыров|Сергей|Николаевич|30|true"));
        repository.create(new User(
                "815e56ed-c2b5-468a-bc09-9ef529c6e2d2|2020-12-25T21:10:11.556|strizh_71|ksdhjf123|ksdhjf123|Стрижов|Алексей|Семенович|50|true"));

        System.out.println("\nРезультаты поиска по полю \"Age\" " + repository.findByAge(30));
    }
}
