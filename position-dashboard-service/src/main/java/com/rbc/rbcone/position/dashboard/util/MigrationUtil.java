package com.rbc.rbcone.position.dashboard.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private static final SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
    private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    private static final String INSERT_ACC_PREFIX = "INSERT INTO account (account_id, account_number, account_name)\n";
    private static final String INSERT_HOLDING_PREFIX = "INSERT INTO holding (holding_id, account_number, portfolio_currency, report_date, country_of_issuer, major_security_type, minor_security_type, security_identifier, cusip_seqcurity_number, security_number_isin, sedol_security_number, long_security_description, units, book_base_value, market_base_value)\n";
    private static final String ACC_VALUES_FORMAT = INSERT_ACC_PREFIX + "VALUES (seq_account.nextVal, '%s', '%s');";
    private static final String HOLDING_VALUES_FORMAT = INSERT_HOLDING_PREFIX + "VALUES (seq_holding.nextVal, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %f, %f, %f);";
    private static final Map<String, String> countryCodeMap = createCountryCodeMap(COUNTRY_CODE_FILE);

    public static String createSqlInsertForAccount(String holdingsFilename) {
        return readFile(holdingsFilename)
                .map(l -> l.split("\\|"))
                .map(arr -> Arrays.asList(Arrays.copyOfRange(arr, 0, 2)))
                .distinct()
                .map(list -> String.format(ACC_VALUES_FORMAT, list.get(0), list.get(1)))
                .collect(Collectors.joining("\n"));
    }

    public static String createSqlInsertForHolding(String holdingsFilename) {
        return readFile(holdingsFilename)
                .map(l -> l.split("\\|"))
                .map(arr -> new Object[]{arr[0], arr[2], toSqlDate(arr[3]), toISOCountryCode(arr[5]), arr[6], arr[7],
                        arr[8], arr[9], arr[10], arr[11], arr[14], toDouble(arr[15]),
                        toDouble(arr[16]), toDouble(arr[17])})
                .map(arr -> String.format(HOLDING_VALUES_FORMAT, arr))
                .collect(Collectors.joining("\n"));
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

    public static String toSqlDate(String datetime) {
        Date date = new Date();
        try {
            date = inputDateFormat.parse(datetime);
        } catch (Exception e) {
            logger.info("Cannot parse date: " + datetime);
        }
        return sqlDateFormat.format(date);
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

        String holdingsfilename = "src/main/resources/data/sampleholdingsdata_csv.csv";

        System.out.println(createSqlInsertForHolding(holdingsfilename));

//        System.out.println(countryCodeMap.get("United States"));
//        System.out.println(countryCodeMap);

    }
}
