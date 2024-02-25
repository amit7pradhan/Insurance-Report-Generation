package in.insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.insurance.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

	 
	  @Query(value="SELECT DISTINCT planName FROM CitizenPlan") 
	  public List<String> getPlanName();
	  @Query(value="SELECT DISTINCT planStatus FROM CitizenPlan") 
	  public List<String> getPlanStatus();
	  
	  
	 
	
}
