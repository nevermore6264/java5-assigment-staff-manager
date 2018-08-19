package com.fpoly.service.impl;

import com.fpoly.model.Record;
import com.fpoly.model.Staff;
import com.fpoly.repository.RecordRepository;
import com.fpoly.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Record findById(Long id) {
        return recordRepository.findOne(id);
    }

    @Override
    public Page<Record> findAll(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public boolean existName(String name) {
        Record record = recordRepository.findByReason(name);
        return (record != null);
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }

    @Override
    public void delete(Long id) {
        recordRepository.delete(id);
    }

    @Override
    public Page<Record> findAllByReasonContains (String name, Pageable pageable) {
        return recordRepository.findAllByReasonContains(name, pageable);
    }

    @Override
    public Page<Record> findAllByStaff(Staff staff, Pageable pageable) {
        return recordRepository.findAllByStaff(staff,pageable);
    }
}

