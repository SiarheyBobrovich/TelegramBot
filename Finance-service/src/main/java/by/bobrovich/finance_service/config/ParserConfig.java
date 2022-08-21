package by.bobrovich.finance_service.config;

import by.bobrovich.finance_service.parsers.api.IMyFinParser;
import by.bobrovich.finance_service.services.api.IFinanceService;
import by.bobrovich.finance_service.thread.FinanceUpdaterThread;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ParserConfig {

    @Bean
    public List<Thread> financeUpdaterThread(IFinanceService service,
                                       List<IMyFinParser> parsers) {
        List<Thread> collect = parsers.stream()
                .map(parser -> new Thread(new FinanceUpdaterThread(service, parser)))
                .collect(Collectors.toList());

        collect.forEach(Thread::start);

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> LogFactory.getLog(t.getName()).error(e));

        return collect;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}