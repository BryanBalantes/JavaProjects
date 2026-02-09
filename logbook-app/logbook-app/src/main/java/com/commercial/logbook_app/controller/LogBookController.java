package com.commercial.logbook_app.controller;

/*
* The controller for handling requests for the Record pages.
*
* @author Bryan
* */

import com.commercial.logbook_app.dto.LogBookDTO;
import com.commercial.logbook_app.service.LogBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/records")
public class LogBookController {
    // Constructor Injection start
        private final LogBookService logBookService;

        public LogBookController(LogBookService logBookService){
            this.logBookService = logBookService;
        }
    // Constructor Injection end


    @GetMapping("/")
    public String listAll(Model model){
        List<LogBookDTO> records = logBookService.getAll();
        model.addAttribute("records", records);
        return "records/list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("record", new LogBookDTO());
        return "records/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("record") LogBookDTO logBookDTO){
      logBookService.create(logBookDTO);
      return "redirect:/records/";
    }
}
