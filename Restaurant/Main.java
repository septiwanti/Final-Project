package Restaurant;

import Restaurant.config.Database;
import Restaurant.views.TerminalViews;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Restaurant")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TerminalViews terminalViews = context.getBean(TerminalViews.class);
        terminalViews.run();
    }

    @Bean
    Database database (){
        Database database = new Database("restaurantDB", "root","", "localhost","3306");
        database.setup();
        return database;
    }
}




