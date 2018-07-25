package com.fpoly.formatter;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import com.fpoly.service.DepartService;
import com.fpoly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class StaffFormatter implements Formatter<Staff> {

    private StaffService staffService;

    @Autowired
    public StaffFormatter(StaffService staffService) {
        this.staffService = staffService;
    }


    @Override
    public Staff parse(String text, Locale locale) throws ParseException {
        return staffService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Staff object, Locale locale) {
        return null;
    }
}
