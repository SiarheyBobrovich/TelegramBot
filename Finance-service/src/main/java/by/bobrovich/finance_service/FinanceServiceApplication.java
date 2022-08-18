package by.bobrovich.finance_service;

import by.bobrovich.finance_service.dao.api.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		IBankRepository.class,
		IOfficeRepository.class,
		IUsdRepository.class,
		IEuroRepository.class,
		IRubRepository.class
})
public class FinanceServiceApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(FinanceServiceApplication.class, args);
	}
}