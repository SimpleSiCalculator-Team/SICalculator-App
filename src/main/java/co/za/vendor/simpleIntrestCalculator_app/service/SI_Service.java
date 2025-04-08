package co.za.vendor.simpleIntrestCalculator_app.service;

import co.za.vendor.simpleIntrestCalculator_app.controller.SiCalculatorController;
import co.za.vendor.simpleIntrestCalculator_app.model.SI;
import co.za.vendor.simpleIntrestCalculator_app.repository.SI_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;


@Service
public class SI_Service {
    @Autowired
    SI_Repository si_repository;
    @Autowired
    SI si ;





    public  String checkForLoan(int age){

        if (age <18  ){
            return " your minor your not eligible for loan";
        }else {
            return " your above 18 your eligible for loan";
        }


    }

    public  String applyForLoan(@RequestBody SI si){
        si_repository.save(si);


        return "new customer details saved to db successfully and the custmer details are" + " ID: " + si.getID() + ", " + "name: " + si.getName() + " taken loan " +
                si.getLoanAmount()+ " for the time " + si.getTime() + " months"+
                " for the intrest rate " + si.getRateOfInterest() + " %";

    }

    public Object calculateForLoan(int id) {
        Optional<SI> optionalSi = si_repository.findById(id);

        if (optionalSi.isPresent()) {
            SI si = optionalSi.get();
            double interest = (si.getLoanAmount() * si.getTime() * si.getRateOfInterest()) / 100;

            return "This is the loan interest of ID: " + si.getID() + " = " + interest;
        } else {
            return "Loan ID not found!";
        }
    }



//    public  Object calculateForLoan(int id){
//       si_repository.findById(id);
//
//       return "this is the loan intrest of ID: " + si.getID() +(si.getLoanAmount()*si.getTime()*si.getRateOfInterest())/100 ;
//
//
//    }


    public Object duAmount(int loanId){

        // calculate loan amount = loan + interest;

        //interest = (p * r * t) / 100;

        Optional<SI> SI = si_repository.findById(loanId);

        if(SI.isPresent()) {

            SI si = SI.get();

            double p = si.getLoanAmount();
            double t = si.getTime();
            double r = si.getRateOfInterest();
            double I = (p * t * r) / 100;

            double dueAmount = p + I;
            return " this is the dueAmount pending: " + dueAmount;
        }else{
            return "There is no such a loanId PLEASE enter correct loan id";
        }




    }



    public Object deleteUser( int loanId, String result){

        Optional<SI> optionalsi = si_repository.findById(loanId);

        if (optionalsi.isPresent()){
            if(result.equals("yes")) {
                si_repository.deleteById(loanId);
                return "the user with ID: " + loanId + " deleted successfully";
            }else{

                return "WITHOUT CLEARING DUES WE CANNOT PROCEED WITH FURTHER";
            }
        }else {
            return "the user with trhe ID: " + loanId + " is no longer exist or user may cleared their dues in time.";
        }


    }



}
