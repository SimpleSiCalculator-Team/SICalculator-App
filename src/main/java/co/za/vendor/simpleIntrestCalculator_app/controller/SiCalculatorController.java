package co.za.vendor.simpleIntrestCalculator_app.controller;

import co.za.vendor.simpleIntrestCalculator_app.model.SI;
import co.za.vendor.simpleIntrestCalculator_app.service.SI_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
public class SiCalculatorController {

    //APPLY FOR LOAN ,CHECK LOAN , CALACULATE LOAN;

    @Autowired
    SI_Service si_service ;
    @Autowired
    SI si ;

    @GetMapping("/eligibility/{age}")
    public  Object checkForLoan(@PathVariable("age") int age ){
        si.setAge(age);
        return si_service.checkForLoan(si.getAge());

    }
    @PostMapping("/applyForLoan")
    public  String applyForLoan(@RequestBody SI si){
      return si_service.applyForLoan(si);
    }
    @GetMapping("/calculateLoan/{loanId}")
    public Object calculateForLoan(@PathVariable("loanId") int loanId) {
        return si_service.calculateForLoan(loanId);
    }

    @GetMapping("/totalDue")
    public Object dueAmount (@RequestParam("loanId") int loanId){

        return si_service.duAmount(loanId);

    }

    @DeleteMapping("/Delete/{loanId}")
    public Object deleteUser(@PathVariable("loanId") int loanId, @RequestParam("result") String result){
        return si_service.deleteUser(loanId,result );

    }

}
