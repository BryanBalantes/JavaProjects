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
import org.springframework.web.bind.annotation.*;

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


    /*
    * Retrieve all LogBook objects
    *
    * @param model ui model
    * @return list page
    * */
    @GetMapping("/")
    public String listAll(Model model){
        List<LogBookDTO> records = logBookService.getAll();
        model.addAttribute("records", records);
        return "records/list";
    }

    /*
     * Retrieve a specific LogBook objects
     *
     * @param model ui model
     * @param id the id of the record to be retrieved
     * @return view page
     * */
    @GetMapping("/{id}")
    public String getViewPage(Model model, @PathVariable int id){
            LogBookDTO logBookDTO = logBookService.getById(id);
            model.addAttribute("record", logBookDTO);
            return "records/view";
    }

    /*
     * Display add LogBook page
     *
     * @param model ui model
     * @return add record page
     * */
    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("record", new LogBookDTO());
        return "records/add";
    }

    /*
     * Create new LogBook object
     *
     * @param logBookDTO the record to be added
     * @return redirects to the list page
     * */
    @PostMapping("/add")
    public String create(@ModelAttribute("record") LogBookDTO logBookDTO){
      logBookService.create(logBookDTO);
      return "redirect:/records/";
    }

    @PutMapping
    public String update(@ModelAttribute("record") LogBookDTO logBookDTO){
        logBookService.update(logBookDTO);
        return "redirect:/records/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        logBookService.delete(id);
        return "redirect:/records/";
    }
}
