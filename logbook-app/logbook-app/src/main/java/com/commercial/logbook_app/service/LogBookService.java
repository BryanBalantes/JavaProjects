package com.commercial.logbook_app.service;

import com.commercial.logbook_app.dto.LogBookDTO;
import com.commercial.logbook_app.model.LogBook;
import com.commercial.logbook_app.repository.LogBookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogBookService {

    private final LogBookRepository logBookRepository;

    public LogBookService(LogBookRepository logBookRepository) {
        this.logBookRepository = logBookRepository;
    }

    /*
    * Retrieve all LogBook objects
    *
    * @return list of LogBook objects
    * */
    public List<LogBookDTO> getAll() {
        return logBookRepository.findAll()
                .stream().map(LogBookDTO::new)
                .collect(Collectors.toList());
    }

    /*
     * Retrieve a specific LogBook objects
     *
     * @Param id the id of the LogBook to be retrieved
     * @return the target LogBook object
     * */
    public LogBookDTO getById(int id) {
        LogBook model = logBookRepository.getReferenceById(id);
        return new LogBookDTO(model);
    }

    /*
     * Create a new LogBook object
     *
     * @param logBookDTO the object to be created
     * */
    public void create(LogBookDTO logBookDTO){
        LogBook model = new LogBook(logBookDTO);
        logBookRepository.save(model);
    }

    /*
     * Update an existing LogBook object
     *
     * @param logBookDTO the object to be updated
     * */
    public void update(LogBookDTO logBookDTO) {
        LogBook model = new LogBook(logBookDTO);
        logBookRepository.save(model);
    }

    public void delete(int id) {
        logBookRepository.deleteById(id);
    }

}
