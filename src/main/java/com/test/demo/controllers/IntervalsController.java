package com.test.demo.controllers;

import com.test.demo.domain.entities.TimeInterval;
import com.test.demo.service.IntervalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    @Autowired
    private IntervalsService service;

    @GetMapping("/")
    @ResponseBody
    public List<TimeInterval> getIntervals() {
        return service.getIntervals();
    }

    @GetMapping("/{id}")
    public TimeInterval getInterval(@PathVariable("id") Long id) throws Exception {
        return service.getInterval(id);
    }

    @PostMapping(value = "/import/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void importIntervals(@RequestBody List<TimeInterval> intervals) throws Exception {
        service.importIntervals(intervals);
    }

    @DeleteMapping("/{id}")
    public void deleteInterval(@PathVariable("id") Long id) {
        service.deleteInterval(id);
    }
}
