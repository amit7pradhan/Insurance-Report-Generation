package in.insurance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Citizen_Plan_Info")
@Data
public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String name;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	private String denialResn;
	private LocalDate terminateDate;
	private String terminateResn;
	
	
	
	
	
	
	
	
	
	
	
	
}
