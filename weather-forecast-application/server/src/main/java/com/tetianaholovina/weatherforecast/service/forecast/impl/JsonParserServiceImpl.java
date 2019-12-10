package com.tetianaholovina.weatherforecast.service.forecast.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tetianaholovina.weatherforecast.service.forecast.ParserService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.LIST;

/**
 * <p>This class has a responsibility of parsing the input json string message and convert it to the
 * JsonNode object which is used for further data processing purpose by the service classes
 */

@Service
public class JsonParserServiceImpl implements ParserService {

    @Override
    public JsonNode parseData(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(data);
        return rootNode.path(LIST);
    }
}
