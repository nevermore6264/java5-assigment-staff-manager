package com.fpoly.controller;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import com.fpoly.service.DepartService;
import com.fpoly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/departs")
public class DepartController {
    private DepartService departService;
    private StaffService staffService;

    @Autowired
    public DepartController(DepartService departService,StaffService staffService) {
        this.departService = departService;
        this.staffService = staffService;
    }

    @GetMapping("")
    public ModelAndView listCountries(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Depart> departs;
        if (s.isPresent()) {
            departs = departService.findAllByNameContains(s.get(), pageable);
        } else {
            departs = departService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/depart/list");
        modelAndView.addObject("departs", departs);
        return modelAndView;
    }

    @GetMapping("/{id}/views")
    public ModelAndView showListRecord(@PathVariable("id") Long id, Pageable pageable) {
        Depart depart = departService.findById(id);
        if (depart == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/depart/views");
            Page<Staff> staffs = staffService.findAllByDepart(depart, pageable);
            modelAndView.addObject("depart",depart);
            modelAndView.addObject("staffs", staffs);
            return modelAndView;
        }
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/depart/create");
        modelAndView.addObject("depart", new Depart());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createDepart(@ModelAttribute("depart") Depart depart) {
        ModelAndView modelAndView = new ModelAndView("/depart/create");
        departService.save(depart);
        modelAndView.addObject("message", "New depart is created");
        modelAndView.addObject("depart", new Depart());
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {

        Depart depart = departService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (depart == null) {
            modelAndView.setViewName("/error404");
            return modelAndView;
        } else {
            modelAndView.setViewName("/depart/delete");
            modelAndView.addObject("depart", depart);
            return modelAndView;
        }
    }


    @PostMapping("/{id}/delete")
    public ModelAndView deleteDepart(@PathVariable("id") Long id) {
        Depart depart = departService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (depart != null) {
            departService.delete(id);
        }
        modelAndView.setViewName("redirect:/departs");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {

        ModelAndView modelAndView;
        Depart depart = departService.findById(id);
        if (depart != null) {
            modelAndView = new ModelAndView("/depart/edit");
            modelAndView.addObject("depart", depart);
            return modelAndView;
        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(@Valid @ModelAttribute("depart") Depart depart) {

        ModelAndView modelAndView = new ModelAndView("/depart/edit");
        departService.save(depart);
        modelAndView.addObject("depart", depart);
        modelAndView.addObject("message", "Update successfully");
        return modelAndView;
    }
}
