package com.test.demo.service.implementation;

import com.test.demo.domain.entities.TimeInterval;
import com.test.demo.exceptions.IntervalListLoadedException;
import com.test.demo.service.IntervalsService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IntervalServiceImpl implements IntervalsService {


    private List<TimeInterval> intervals = new ArrayList<>();

    @Override
    public void setPlaceholderIntervals() throws IntervalListLoadedException {
        if (this.intervals.isEmpty()) {
            this.intervals.add(new TimeInterval(1L, "24/06/2014 08:22:07", "28/12/2016 12:10:14"));
        } else {
            throw new IntervalListLoadedException("List is already loaded - Cancelling");
        }
    }

    @Override
    public void importIntervals(List<TimeInterval> intervalArray) throws Exception {

        if (intervalArray.isEmpty()) {
            throw new Exception("Interval List is empty");
        }

        this.intervals.addAll(intervalArray);

        calculateIntervalsDuration(this.intervals);
        calculateIntervalsBreakPeriod(this.intervals);

    }


    public void calculateIntervalsDuration(List<TimeInterval> intervals) {

        for (TimeInterval interval : intervals) {
            String diff = findDifference(interval.getEnd(), interval.getStart());
            interval.setIntervalDuration(diff);
        }
    }

    public void calculateIntervalsBreakPeriod(List<TimeInterval> intervals) {

        for (int i = 1; i < intervals.size(); i++) {
            String diff = findDifference(intervals.get(i - 1).getEnd(), intervals.get(i).getStart());
            intervals.get(i).setBreakDuration(diff);
        }
    }

    @Override
    public List<TimeInterval> getIntervals() {
        return this.intervals;
    }

    @Override
    public TimeInterval getInterval(Long id) throws Exception {
        return this.intervals.stream().filter(interval -> interval.getId().equals(id)).findAny()
                .orElseThrow(() -> new Exception("No Interval Found with given Id" + id));
    }

    @Override
    public void deleteInterval(Long id) {
        for (int i = 0; i < this.intervals.size(); i++) {
            if (this.intervals.get(i).getId().equals(id)) {
                this.intervals.remove(i);
            }
        }
    }

    private String findDifference(String start_date, String end_date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String builtStringDate = "";

        try {
            Date d1 = sdf.parse(end_date);
            Date d2 = sdf.parse(start_date);

            long timeDifference = d2.getTime() - d1.getTime();


            long seconds = (timeDifference / 1000) % 60;
            long minutes = (timeDifference / (1000 * 60)) % 60;
            long hours = (timeDifference / (1000 * 60 * 60)) % 24;
            long days = (timeDifference / (1000 * 60 * 60 * 24)) % 365;

            builtStringDate = "Days: " + days + " - " + hours + ":" + minutes + ":" + seconds;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return builtStringDate;
    }
}
