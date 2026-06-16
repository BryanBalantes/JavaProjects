package com.commercial.logbook_app.controller;

/*
 * The controller for handling requests for the Record pages.
 *
 * @author Bryan
 * */

import com.commercial.logbook_app.dto.TodoDTO;
import com.commercial.logbook_app.service.TodoService;
import com.commercial.logbook_app.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {
  // Constructor Injectio start
  private final TodoService todoService;

  private final UserService userService;

  public TodoController(TodoService todoService, UserService userService) {
    this.todoService = todoService;
    this.userService = userService;
  }

  // Constructor Injection end

  /*
   * Retrieve all LogBook objects
   *
   * @param model ui model
   * @return list page
   * */
  @GetMapping("/")
  public String listAll(Model model) {
    List<TodoDTO> todos = todoService.getAll();
    model.addAttribute("todos", todos);
    return "todos/list";
  }

  /*
   * Retrieve a specific LogBook objects
   *
   * @param model ui model
   * @param id the id of the record to be retrieved
   * @return view page
   * */
  @GetMapping("/{id}")
  public String getViewPage(Model model, @PathVariable int id) {
    TodoDTO todoDTO = todoService.getById(id);
    model.addAttribute("todo", todoDTO);
    model.addAttribute("users", userService.getAll());
    return "todos/view";
  }

  /*
   * Display add LogBook page
   *
   * @param model ui model
   * @return add record page
   * */
  @GetMapping("/add")
  public String getAddPage(Model model) {
    model.addAttribute("todo", new TodoDTO());
    model.addAttribute("users", userService.getAll());
    return "todos/add";
  }

  /*
   * Create new LogBook object
   *
   * @param logBookDTO the record to be added
   * @return redirects to the list page
   * */
  @PostMapping("/add")
  public String create(@ModelAttribute("todo") TodoDTO todoDTO) {
    todoService.create(todoDTO);
    return "redirect:/todos/";
  }

  @PutMapping
  public String update(@ModelAttribute("record") TodoDTO todoDTO) {
    todoService.update(todoDTO);
    return "redirect:/todos/";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    todoService.delete(id);
    return "redirect:/todos/";
  }
}
