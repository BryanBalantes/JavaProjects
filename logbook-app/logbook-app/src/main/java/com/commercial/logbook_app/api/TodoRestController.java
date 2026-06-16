package com.commercial.logbook_app.api;

import com.commercial.logbook_app.dto.TodoDTO;
import com.commercial.logbook_app.service.TodoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

  private final TodoService todoService;

  public TodoRestController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public List<TodoDTO> getAll() {
    return todoService.getAll();
  }

  @GetMapping("/{id}")
  public TodoDTO getById(@PathVariable int id) {
    return todoService.getById(id);
  }

  @PostMapping
  public void create(@RequestBody TodoDTO todoDTO) {
    todoService.create(todoDTO);
  }


}
