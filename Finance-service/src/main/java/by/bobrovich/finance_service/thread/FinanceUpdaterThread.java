package by.bobrovich.finance_service.thread;

import by.bobrovich.finance_service.dao.entity.Bank;
import by.bobrovich.finance_service.parsers.api.IMyFinParser;
import by.bobrovich.finance_service.services.api.IFinanceService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FinanceUpdaterThread implements Runnable, AutoCloseable {

    private final IFinanceService service;
    private final IMyFinParser parser;
    boolean isInterrupted = false;

    public FinanceUpdaterThread(IFinanceService service, IMyFinParser parser) {
        this.service = service;
        this.parser = parser;
    }

    @Override
    public void run() {
        while (!isInterrupted) {
            List<Bank> banks = parser.getBanks();
            service.saveAll(banks);

            System.err.println("completed: " + parser);
            try {
                TimeUnit.HOURS.sleep(1);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isInterrupted = true;
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.isInterrupted = true;
    }
}
