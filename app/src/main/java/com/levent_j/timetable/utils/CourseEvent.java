package com.levent_j.timetable.utils;

/**
 * Created by levent_j on 16-11-21.
 */
public class CourseEvent {
    private String data;
    private String begin;
    private String end;

    public CourseEvent(String data, String begin, String end) {
        this.data = data;
        this.begin = begin;
        this.end = end;
    }

    public String getData() {
        return data;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }
}
