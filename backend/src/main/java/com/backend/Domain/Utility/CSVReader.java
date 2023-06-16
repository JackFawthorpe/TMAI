package com.backend.Domain.Utility;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVReader {

    /**
     * Reads all lines of the card CSV
     * @return A list of string array's one for each line of the CSV
     * @throws IOException When the CSV cannot be read
     * @throws URISyntaxException Invalid URI (Not possible)
     */
    public List<String[]> readAllLines(String filename) throws IOException, URISyntaxException {
        Path path = Paths.get(
                ClassLoader.getSystemResource(filename).toURI());
        try (Reader reader = Files.newBufferedReader(path)) {
            try (com.opencsv.CSVReader csvReader = new com.opencsv.CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

}
