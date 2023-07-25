package core.basesyntax;

import core.basesyntax.files.FileReaderService;
import core.basesyntax.files.FileReaderServiceImpl;
import core.basesyntax.files.FileWriterService;
import core.basesyntax.files.FileWriterServiceImpl;
import core.basesyntax.service.DataInputParser;
import core.basesyntax.service.DataInputParserImpl;
import core.basesyntax.service.DataOutputParser;
import core.basesyntax.service.DataOutputParserImpl;
import core.basesyntax.service.FruitTransaction;
import core.basesyntax.service.OperationProcess;
import core.basesyntax.service.OperationProcessImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.List;

public class Main {
    private static final String INPUT_FILE_PATH = "src\\main\\resources\\test.csv";
    private static final String OUTPUT_FILE_PATH = "src\\main\\resources\\dailyReport";

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        DataInputParser dataParser = new DataInputParserImpl();
        List<String> dataFromFile = fileReaderService
                .readFromFile(INPUT_FILE_PATH);
        List<FruitTransaction> fruitTransactionList = dataParser.parseData(dataFromFile);
        System.out.println(fruitTransactionList);
        OperationStrategy operationStrategy = new OperationStrategyImpl();
        OperationProcess operationProcess = new OperationProcessImpl(operationStrategy);
        operationProcess.processData(fruitTransactionList);
        DataOutputParser dataOutputParser = new DataOutputParserImpl();
        String report = dataOutputParser.parseData();
        FileWriterService fileWriterService = new FileWriterServiceImpl();
        fileWriterService.write(report, OUTPUT_FILE_PATH);
    }
}
