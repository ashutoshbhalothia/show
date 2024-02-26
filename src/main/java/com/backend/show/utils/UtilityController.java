package com.backend.show.utils;

import com.backend.show.scheduler.HealthCheckScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UtilityController extends BaseUtilController{

    private final HealthCheckScheduler healthCheckScheduler;

    @GetMapping("health-check")
    public void handleHealthCheck(){
        healthCheckScheduler.scheduleHealthCheck();
    }
}
