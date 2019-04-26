package io.pivotal.pal.calc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calc-entries")
public class CalcEntryController {

    private CalcEntryRepository calcEntryRepository;

    public CalcEntryController(CalcEntryRepository calcEntryRepository) {
        this.calcEntryRepository = calcEntryRepository;
    }

    @PostMapping
    public ResponseEntity<CalcEntry> create(@RequestBody CalcEntry calcEntryToCreate) {
        CalcEntry createdCalcEntry = calcEntryRepository.create(calcEntryToCreate);

        return new ResponseEntity<>(createdCalcEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CalcEntry>> list() {
        return new ResponseEntity<>(calcEntryRepository.list(), HttpStatus.OK);
    }
}
