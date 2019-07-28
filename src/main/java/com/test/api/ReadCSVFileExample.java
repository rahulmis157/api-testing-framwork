package com.test.api;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
 
public class ReadCSVFileExample {
 
    static final String CSV_FILENAME = "/home/rahul/Documents/api-test-project/api-testing-framwork/src/main/java/com/test/api/resource/data.csv";
 
    public static void main(String[] args) throws IOException
    {
        try(ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME), CsvPreference.STANDARD_PREFERENCE))
        {
            // the header elements are used to map the values to the bean
            final String[] headers = beanReader.getHeader(true);
            //final String[] headers = new String[]{"CustomerId","CustomerName","Country","PinCode","Email"};
            final CellProcessor[] processors = getProcessors();
 
            Customer customer;
            List<Customer> cust = new ArrayList<>();
            while ((customer = beanReader.read(Customer.class, headers, processors)) != null) {
            	cust.add(customer);
            }
            System.out.println(cust.size());
        }
    }
 
    /**
     * Sets up the processors used for the examples.
     */
    private static CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(new ParseInt()), // CustomerId
                new NotNull(), // CustomerName
                new NotNull()
        };
        return processors;
    }
}