package in.insurance.runners;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.insurance.entity.CitizenPlan;
import in.insurance.repo.CitizenPlanRepo;
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		//Cash Plan Date
		CitizenPlan c1 = new CitizenPlan();
		c1.setName("Jhon");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setStartDate(LocalDate.now());
		c1.setEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000);
		//Cash Plan Date
		CitizenPlan c2 = new CitizenPlan();
		c2.setName("Smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialResn("Rental Income");
		//Cash Plan Date
		CitizenPlan c3 = new CitizenPlan();
		c3.setName("Gimini");
		c3.setGender("Female");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setStartDate(LocalDate.now().minusMonths(4));
		c3.setEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(5000);
		c3.setTerminateDate(LocalDate.now());
		c3.setTerminateResn("Employed");
		//Medical Plan Data
		CitizenPlan c4 = new CitizenPlan();
		c4.setName("Axila");
		c4.setGender("Female");
		c4.setPlanName("Medical");
		c4.setPlanStatus("Approved");
		c4.setStartDate(LocalDate.now());
		c4.setEndDate(LocalDate.now().plusMonths(12));
		c4.setBenefitAmount(10000);
		//Medical Plan Data
		CitizenPlan c5 = new CitizenPlan();
		c5.setName("Joshef");
		c5.setGender("Male");
		c5.setPlanName("Medical");
		c5.setPlanStatus("Denied");
		c5.setDenialResn("Property");
		//Medical Plan Data
		CitizenPlan c6 = new CitizenPlan();
		c6.setName("Harry");
		c6.setGender("Male");
		c6.setPlanName("Medical");
		c6.setPlanStatus("Terminated");
		c6.setStartDate(LocalDate.now().minusMonths(3));
		c6.setEndDate(LocalDate.now().plusMonths(12));
		c6.setBenefitAmount(10000);
		c6.setTerminateDate(LocalDate.now());
		c6.setTerminateResn("Income Source");
		//Food Plan Data
		CitizenPlan c7 = new CitizenPlan();
		c7.setName("Donld");
		c7.setGender("Male");
		c7.setPlanName("Food");
		c7.setPlanStatus("Approved");
		c7.setStartDate(LocalDate.now());
		c7.setEndDate(LocalDate.now().plusMonths(8));
		c7.setBenefitAmount(3000);
		//Food Plan Data
		CitizenPlan c8 = new CitizenPlan();
		c8.setName("Saina");
		c8.setGender("Female");
		c8.setPlanName("Food");
		c8.setPlanStatus("Denied");
		c8.setDenialResn("Illegal Activities");
		//Food Plan Data
		CitizenPlan c9 = new CitizenPlan();
		c9.setName("Roman");
		c9.setGender("Male");
		c9.setPlanName("Food");
		c9.setPlanStatus("Terminated");
		c9.setStartDate(LocalDate.now().minusMonths(2));
		c9.setEndDate(LocalDate.now().plusMonths(3));
		c9.setBenefitAmount(10000);
		c9.setTerminateDate(LocalDate.now());
		c9.setTerminateResn("Goverment Job");
		//Vehicle Plan Data
		CitizenPlan c10 = new CitizenPlan();
		c10.setName("Satya");
		c10.setGender("Male");
		c10.setPlanName("Vehicle");
		c10.setPlanStatus("Approved");
		c10.setStartDate(LocalDate.now());
		c10.setEndDate(LocalDate.now().plusMonths(24));
		c10.setBenefitAmount(8000);
		//Vehicle Plan Data
		CitizenPlan c11 = new CitizenPlan();
		c11.setName("Scanda");
		c11.setGender("Male");
		c11.setPlanName("Vehicle");
		c11.setPlanStatus("Denied");
		c11.setDenialResn("Accidental Issues");
		//Vehicle Plan Data
		CitizenPlan c12 = new CitizenPlan();
		c12.setName("Diblin");
		c12.setGender("Female");
		c12.setPlanName("Vehicle");
		c12.setPlanStatus("Terminated");
		c12.setStartDate(LocalDate.now().minusMonths(1));
		c12.setEndDate(LocalDate.now().plusMonths(4));
		c12.setBenefitAmount(10000);
		c12.setTerminateDate(LocalDate.now());
		c12.setTerminateResn("Not Paying EMI");
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		repo.saveAll(list);
		
		
		
		
		

	}

}
