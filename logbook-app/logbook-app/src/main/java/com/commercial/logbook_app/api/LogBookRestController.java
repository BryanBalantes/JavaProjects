package com.commercial.logbook_app.api;

import com.commercial.logbook_app.dto.LogBookDTO;
import com.commercial.logbook_app.service.LogBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class LogBookRestController {

    @Autowired
    private LogBookService logBookService;

    @GetMapping
    public List<LogBookDTO> getAll() {
        return logBookService.getAll();
    }

    @GetMapping("/{id}")
    public LogBookDTO getById(@PathVariable int id){
        return logBookService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody LogBookDTO logBookDTO){
        logBookService.create(logBookDTO);
    }
}
