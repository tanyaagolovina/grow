package com.tetianaholovina.weatherforecast.service.forecast.integ;

import com.fasterxml.jackson.databind.JsonNode;
import com.tetianaholovina.weatherforecast.WeatherForecastApplication;
import com.tetianaholovina.weatherforecast.service.forecast.ParserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WeatherForecastApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class JsonParserServiceImplTest {

    @Autowired
    ParserService parserService;

    private String content;

    @Before
    public void setUp() throws IOException {
        ClassPathResource resource = new ClassPathResource("src/test/resources/testData/weatherForecastData.json");
        File file = new File(resource.getPath());
        content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    }

    @Test
    public void parseData() throws IOException {
        JsonNode jsonNode = parserService.parseData(content);

        assertNotNull(jsonNode.path("dt_txt").asText());
        assertNotNull(jsonNode.path("list").path("main").path("temp"));
        assertNotNull(jsonNode.path("list").path("main").path("pressure"));
    }
}
