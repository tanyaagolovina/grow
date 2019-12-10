package com.tetianaholovina.weatherforecast.utilsForTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestUtils {
    private TestUtils(){}

    public static JsonNode getJsonNode(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(data);
        return rootNode.get("list");
    }

    public static List<LocalDate> getRequiredDatesFromRange(double numberOfDays){
        long noOfDays = (long) numberOfDays;
        return IntStream.iterate(0, i -> i+1)
                .limit(noOfDays)
                .mapToObj(i -> LocalDate.now().plusDays(i))
                .collect(Collectors.toList());
    }
}
