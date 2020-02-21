package com.fpoly.service.impl;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import com.fpoly.repository.StaffRepository;
import com.fpoly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff findById(Long id) {
        return staffRepository.findOne(id);
    }

    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public boolean existName(String name) {
        Staff staff = staffRepository.findByName(name);
        return (staff != null);
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.delete(id);
    }

    @Override
    public Page<Staff> findAllByNameContains(String name, Pageable pageable) {
        return staffRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public Page<Staff> findAllByDepart(Depart depart, Pageable pageable) {
        return staffRepository.findAllByDepart(depart, pageable);
    }

    @Override
    public List<Staff> findByDepart(Depart depart) {
        return staffRepository.findByDepart(depart);
    }
}
