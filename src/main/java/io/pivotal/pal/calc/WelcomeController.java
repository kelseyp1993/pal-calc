//package io.pivotal.pal.calc;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class WelcomeController {
//
//    public String message;
//
//    public WelcomeController(@Value("${welcome.message}") String message) {
//        this.message = message;
//    }
//
//    @GetMapping("/")
//    public String sayHello(){
//        return message;
//    }
//
////    @GetMapping("/")
////    public String index(Model model){
////        model.addAttribute("calc", new CalcEntry());
////        model.addAttribute("calcHistory", new InMemoryCalcEntryRepository());
////        return "index";}
////
////    @PostMapping("/")
////    public String calcResult(@ModelAttribute CalcEntry calc){
////        //need to call calcEntryController API entry to calculate
////        return "index";
////    }
//
////    @GetMapping("/calc")
////    public String calcForm(Model model){
////        model.addAttribute("calc", new CalcEntry());
////        return "calc";
////    }
//
////    @PostMapping("/calc")
////    public String calcResult(@ModelAttribute CalcEntry calc){
////        return "calcResult";
////    }
//
//}
