package com.rbc.rbcone.position.dashboard.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wayneyu on 1/4/17.
 */
public class MigrationUtil {

    private static Logger logger = LoggerFactory.getLogger(MigrationUtil.class);

    private static final String COUNTRY_CODE_FILE = "src/main/resources/data/country_code.csv";
    private static final String INSERT_ACC_PREFIX = "INSERT INTO account (account_id, account_number, account_name)\n";
    private static final String INSERT_HOLDING_PREFIX = "INSERT INTO holding (holding_id, account_number, portfolio_currency, report_date, country_of_issuer, major_security_type, minor_security_type, security_identifier, cusip_seqcurity_number, security_number_isin, sedol_security_number, long_security_description, units, book_base_value, price, market_base_value)\n";
    private static final String ACC_VALUES_FORMAT = INSERT_ACC_PREFIX + "VALUES (seq_account.nextVal, '%s', '%s');";
    private static final String HOLDING_VALUES_FORMAT = INSERT_HOLDING_PREFIX + "VALUES (seq_holding.nextVal, '%s', '%s', " +
            "TO_DATE('%s', 'yyyy/mm/dd hh24:mi:ss'), '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %f, %f, %f, %f);";
    private static final Map<String, String> countryCodeMap = createCountryCodeMap(COUNTRY_CODE_FILE);

    public static String createSqlInsertForAccount(String holdingsFilename) {
        return readFile(holdingsFilename)
                .map(l -> l.split("\\|"))
                .map(arr -> Arrays.asList(Arrays.copyOfRange(arr, 1, 3)))
                .distinct()
                .map(list -> String.format(ACC_VALUES_FORMAT, list.get(0), list.get(1)))
                .collect(Collectors.joining("\n"));
    }

    public static String createSqlInsertForHolding(String holdingsFilename) {
        return readFile(holdingsFilename)
                .map(l -> l.split("\\|"))
                .map(arr -> new Object[]{arr[1], arr[3], arr[0], toISOCountryCode(arr[7]), arr[9], arr[8],
                        arr[10], arr[4], arr[6], arr[5], arr[11].replaceAll("'", "''" ), toDouble(arr[12]), toDouble(arr[13]),
                        toDouble(arr[14]), toDouble(arr[15])})
                .map(arr -> String.format(HOLDING_VALUES_FORMAT, arr))
                .collect(Collectors.joining("\n"));
    }

    private static void writeToFile(String filename, String content) {
        Path path = FileSystems.getDefault().getPath(filename);
        try {
            Files.write(path, content.getBytes(), StandardOpenOption.WRITE);
        } catch (Exception ex) {
            logger.error("Cannot write to file: " + filename);
        }
    }

    private static double toDouble(String doubleStr) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        try {
            return nf.parse(doubleStr).doubleValue();
        } catch (Exception ex) {
            logger.error("Cannot parse to double: " + doubleStr);
        }
        return 0.0;
    }

    private static String toISOCountryCode(String countryName) {
        countryName = countryName.replace("C I ", "");

        String code = countryCodeMap.get(countryName.toLowerCase());
        return code == null ? countryName : code;
    }

    public static Map<String, String> createCountryCodeMap(String filename) {
        return readFile(COUNTRY_CODE_FILE)
                .map(l -> l.split(","))
                .collect(Collectors.toMap(e -> e[0].toLowerCase(), e -> e[1]));
    }

    public static Stream<String> readFile(String filename) {
        try {
            Path path = FileSystems.getDefault().getPath(filename);
            return Files.lines(path, Charset.forName("UTF-8"));
        } catch (Exception ex) {
            logger.error("Cannot read file: " + filename);
        }
        return new ArrayList<String>().stream();
    }

    public static void main(String[] args) {

//        String holdingsfilename = "src/main/resources/data/sampleholdingsdata_csv.csv";
        String holdingsfilename = "src/main/resources/data/global_sample2.csv";
        String accountsMigrationScript = "src/main/resources/db/migration/V0.0.1/V0.0.1_2__populate_account.sql";
        String holdingsMigrationScript = "src/main/resources/db/migration/V0.0.1/V0.0.1_3__populate_holding.sql";

//        writeToFile(accountsMigrationScript, createSqlInsertForAccount(holdingsfilename));
        writeToFile(holdingsMigrationScript, createSqlInsertForHolding(holdingsfilename));
//        System.out.println("'A".replaceAll("'", "\\\\'" ));
    }
}
