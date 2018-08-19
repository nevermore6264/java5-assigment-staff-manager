package com.fpoly.formatter;

import com.fpoly.model.Depart;
import com.fpoly.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DepartFormatter implements Formatter<Depart> {

    private DepartService departService;

    @Autowired
    public DepartFormatter(DepartService departService) {
        this.departService = departService;
    }


    @Override
    public Depart parse(String text, Locale locale) throws ParseException {
        return departService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Depart object, Locale locale) {
        return null;
    }
}
