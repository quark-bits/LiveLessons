import datamodels.CurrencyConversion;
import datamodels.TripRequest;
import lombok.SneakyThrows;
import tests.ReactorTests;
import tests.RxJavaTests;
import utils.Options;
import utils.RunTimer;
import utils.TestDataFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This program applies WebFlux and Project Reactor features to
 * implement an airline reservations app that synchronously and
 * asynchronously communicates with various microservices to find the
 * best price for flight legs and convert from US dollars to other
 * currencies.  The best price is displayed after the microservices
 * have completed their computations.
 */
public class ex5 {
    /**
     * Debugging tag used by the logger.
     */
    private final String TAG = getClass().getSimpleName();

    /**
     * The trip flight leg used for the tests.
     */
    private final TripRequest mTrip = TripRequest
        .valueOf(LocalDateTime.parse("2025-01-01T07:00:00"),
                 LocalDateTime.parse("2025-02-01T19:00:00"),
                 "LHR",
                 "JFK");

    /**
     * Indicate a conversion of US dollars to British pounds.
     */
    private final CurrencyConversion mCurrencyConversion = CurrencyConversion
        .valueOf("USD", "GBP", Options.instance().defaultRate());

    /**
     * The Java execution environment requires a static main() entry
     * point method to run the app.
     */
    public static void main(String[] args) {
        // Parse the command-line arguments.
        Options.instance().parseArgs(args);

        // Run the test program.
        new ex5().run();
    }

    /**
     * Run the test program.
     */
    private void run() {

        TestDataFactory.findFlights(mTrip).forEach(System.out::println);
/*
        RunTimer
            // This test invokes microservices to asynchronously
            // determine the best price for a flight from London to
            // New York city in British pounds.
            .timeRun(() -> ReactorTests.runAsyncTests(mTrip,
                                                      mCurrencyConversion),
                     "runAsyncTests");

        RunTimer
            // This test invokes microservices to synchronously
            // determine the best price for a flight from London to
            // New York city in British pounds.
            .timeRun(() -> ReactorTests.runSyncTests(mTrip,
                                                     mCurrencyConversion),
                     "runSyncTests");

        RunTimer
            // This test invokes microservices to asynchronously
            // determine the best price for a flight from London to
            // New York city in British pounds.
            .timeRun(()-> RxJavaTests.runAsyncTestsRx(mTrip,
                                                      mCurrencyConversion),
                     "runAsyncTestsRx");

        // Print the results sorted from fastest to slowest.
        Options.print(RunTimer.getTimingResults());

 */
    }
}
    
