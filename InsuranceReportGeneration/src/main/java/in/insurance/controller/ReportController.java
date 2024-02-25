package in.insurance.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.insurance.entity.CitizenPlan;
import in.insurance.request.SearchRequest;
import in.insurance.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		//model.addAttribute("search", new SearchRequest());
		model.addAttribute("name", service.getPlanName());
		model.addAttribute("status", service.getPlanStatus());
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
		//System.out.println(request);
		//If you don't use @ModelAttribut write the line
		//model.addAttribute("search", new SearchRequest());
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		
		response.addHeader("content-Disposition", "attachment ; filename=palns.xls");
		
		boolean status = service.exportExcel(response);
		
	}

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		
		response.addHeader("content-Disposition", "attachment ; filename=palns.pdf");
		
		boolean status = service.exportPdf(response);
		

	}
	
	
	
}
