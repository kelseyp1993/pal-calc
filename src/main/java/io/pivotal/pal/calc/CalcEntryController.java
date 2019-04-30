package io.pivotal.pal.calc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class CalcEntryController {

    InMemoryCalcEntryRepository calcEntryRepository = new InMemoryCalcEntryRepository();

    public CalcEntryController(CalcEntryRepository calcEntryRepository) {
        //this.calcEntryRepository = calcEntryRepository;
    }

    @GetMapping("/")
    public String calcForm(Model model){
        model.addAttribute("calc", new CalcEntry());
        return "calc";
    }

    @PostMapping("/calc")
    public String calcResult(@ModelAttribute CalcEntry calcEntry){
        //List<CalcEntry> calcEntryHistory = calcEntryRepository.list();
        System.out.println(calcEntry.toString());
        calcEntry = calcEntryRepository.create(calcEntry);
        System.out.println(calcEntry.toString());
        return "calcResult";
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
