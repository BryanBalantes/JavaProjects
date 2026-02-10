package com.commercial.logbook_app.service;

import com.commercial.logbook_app.dto.LogBookDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogBookService {
    private Map<Integer, LogBookDTO> recordList = new HashMap<>();

    public List<LogBookDTO> getAll() {
        return new ArrayList<>(recordList.values());
    }

    public LogBookDTO getById(int id) {
        return recordList.get(id);
    }

    public void create(LogBookDTO logBookDTO){
        recordList.put(logBookDTO.getId(), logBookDTO);
    }

    public void update(LogBookDTO logBookDTO) {
        recordList.put(logBookDTO.getId(), logBookDTO);
    }

    public void delete(int id) {
        recordList.remove(id);
    }

}
