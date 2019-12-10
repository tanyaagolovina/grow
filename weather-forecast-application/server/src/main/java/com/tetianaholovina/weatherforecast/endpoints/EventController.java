package com.tetianaholovina.weatherforecast.endpoints;

import com.tetianaholovina.weatherforecast.business.EventBusiness;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.domain.Plan;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RequestMapping("/profile")
@Api
@RestController
public class EventController {

    private EventBusiness eventBusiness;

    public EventController(EventBusiness eventBusiness) {
        this.eventBusiness = eventBusiness;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Plan>> getAllEvents(){
        return ResponseEntity.ok().body(eventBusiness.getAllEvents());
    }

    @PostMapping("/events")
    public ResponseEntity<Plan> createEvent(@RequestParam(value = "title") String title,
                                             @RequestParam(value = "address") String address,
                                             @RequestParam(value = "dateAndTime") String dateAndTime,
                                             @RequestParam(value = "usersEmail", required = false) String email
                                             ) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException {

        return ResponseEntity.ok().body(eventBusiness.createEvent(title, address, dateAndTime, email));
    }
}
