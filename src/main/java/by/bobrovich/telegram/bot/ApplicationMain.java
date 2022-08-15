package by.bobrovich.telegram.bot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.TimeZone;

public class ApplicationMain {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("by.bobrovich.telegram.bot");
        context.refresh();
    }
}
