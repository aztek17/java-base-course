import org.flywaydb.core.Flyway;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream =
                     new FileInputStream("/Users/Kseniya/projects/java-base-course/src/homeworks/homework17/src/main/resources/db.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataSource dataSource = new DriverManagerDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );

        Flyway flyway = Flyway
                .configure()
                .dataSource(dataSource)
                .locations("classpath:migrations")
                .schemas("shop")
//                .defaultSchema("shop")
//                .cleanDisabled(false)
                .load();

//        flyway.clean();
        flyway.migrate();
        System.out.println("Миграция применена");



    }

}
