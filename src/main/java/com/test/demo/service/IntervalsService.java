package com.test.demo.service;

import com.test.demo.domain.entities.TimeInterval;
import com.test.demo.exceptions.IntervalListLoadedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntervalsService {

    public void setPlaceholderIntervals() throws IntervalListLoadedException;

    public void importIntervals(List<TimeInterval> intervalArray) throws Exception;

    public List<TimeInterval> getIntervals();

    public TimeInterval getInterval(Long id) throws Exception;

    public void deleteInterval(Long id);

}
