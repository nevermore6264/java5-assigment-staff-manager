package com.fpoly.controller;

import com.fpoly.model.Depart;
import com.fpoly.model.Record;
import com.fpoly.model.Staff;
import com.fpoly.service.DepartService;
import com.fpoly.service.RecordService;
import com.fpoly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staffs")
public class StaffController {
    private DepartService departService;
    private StaffService staffService;
    private RecordService recordService;

    @Autowired
    public StaffController(DepartService departService, StaffService staffService, RecordService recordService) {
        this.departService = departService;
        this.staffService = staffService;
        this.recordService = recordService;
    }

    @GetMapping("")
    public ModelAndView listCountries(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Staff> staffs;
        if (s.isPresent()) {
            staffs = staffService.findAllByNameContains(s.get(), pageable);
        } else {
            staffs = staffService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/staff/list");
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }

    @GetMapping("/{id}/views")
    public ModelAndView showListRecord(@PathVariable("id") Long id, Pageable pageable) {
        Staff staff = staffService.findById(id);
        if (staff == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/staff/views");
            Page<Record> records = recordService.findAllByStaff(staff, pageable);
            modelAndView.addObject("staff", staff);
            modelAndView.addObject("records", records);
            return modelAndView;
        }
    }

    @ModelAttribute("departs")
    public Page<Depart> getAllDepart(Pageable pageable) {
        Page<Depart> departs = departService.findAll(pageable);
        return departs;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/staff/create");
        modelAndView.addObject("staff", new Staff());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStaff(@ModelAttribute("staff") Staff staff) {
        ModelAndView modelAndView = new ModelAndView("/staff/create");

        staffService.save(staff);
        modelAndView.addObject("message", "New staff is created");
        modelAndView.addObject("staff", new Staff());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Staff staff = staffService.findById(id);
        if (staff == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/staff/edit");
            modelAndView.addObject("staff", staff);
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateStaff(@Valid @ModelAttribute("staff") Staff staff) {
        ModelAndView modelAndView = new ModelAndView("/staff/edit");
        staffService.save(staff);
        modelAndView.addObject("staff", staff);
        modelAndView.addObject("message", "Staff is updated");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Staff staff = staffService.findById(id);
        ModelAndView modelAndView;
        if (staff != null) {
            modelAndView = new ModelAndView("/staff/delete");
            modelAndView.addObject("staff", staff);
        } else {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteStaff(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/staffs");
        Staff staff = staffService.findById(id);
        List<Record> recordList = recordService.findByStaff(staff);
        if (staff != null) {
            if (!recordList.isEmpty()) {
                modelAndView.setViewName("/error500");
                return modelAndView;
            } else {
                staffService.delete(id);
            }
        } else {
            modelAndView = new ModelAndView("/error404");
        }

        return modelAndView;
    }
}
