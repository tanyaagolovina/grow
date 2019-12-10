package com.tetianaholovina.weatherforecast.service.forecast;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public interface ParserService {
    JsonNode parseData(String data) throws IOException;
}
