package com.commercial.logbook_app.api;

import com.commercial.logbook_app.dto.LogBookDTO;
import com.commercial.logbook_app.service.LogBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class LogBookRestController {

<<<<<<< HEAD
    private final LogBookService logBookService;

    public LogBookRestController(LogBookService logBookService){
        this.logBookService = logBookService;
    }
=======
    @Autowired
    private LogBookService logBookService;
>>>>>>> b3aec1878e0f9e2007d3c4f480efa2d388d5d6c1

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
