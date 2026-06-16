package com.commercial.logbook_app.service;

import com.commercial.logbook_app.dto.TodoDTO;
import com.commercial.logbook_app.model.Todo;
import com.commercial.logbook_app.repository.TodoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  /*
   * Retrieve all LogBook objects
   *
   * @return list of LogBook objects
   * */
  public List<TodoDTO> getAll() {
    return todoRepository.findAll().stream().map(TodoDTO::new).collect(Collectors.toList());
  }

  /*
   * Retrieve a specific LogBook objects
   *
   * @Param id the id of the LogBook to be retrieved
   * @return the target LogBook object
   * */
  public TodoDTO getById(int id) {
    Todo model = todoRepository.getReferenceById(id);
    return new TodoDTO(model);
  }

  /*
   * Create a new LogBook object
   *
   * @param logBookDTO the object to be created
   * */
  public void create(TodoDTO todoDTO) {
    Todo model = new Todo(todoDTO);
    todoRepository.save(model);
  }

  /*
   * Update an existing LogBook object
   *
   * @param logBookDTO the object to be updated
   * */
  public void update(TodoDTO todoDTO) {
    Todo model = new Todo(todoDTO);
    todoRepository.save(model);
  }

  public void delete(int id) {
    todoRepository.deleteById(id);
  }
}
