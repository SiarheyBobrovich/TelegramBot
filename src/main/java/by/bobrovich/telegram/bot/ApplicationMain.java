package by.bobrovich.telegram.bot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.bobrovich.telegram.bot");
        context.refresh();
    }
}
