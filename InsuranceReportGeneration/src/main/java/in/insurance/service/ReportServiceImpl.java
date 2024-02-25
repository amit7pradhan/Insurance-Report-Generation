package in.insurance.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.insurance.entity.CitizenPlan;
import in.insurance.repo.CitizenPlanRepo;
import in.insurance.request.SearchRequest;
import in.insurance.util.EmailUtils;
import in.insurance.util.ExcelGenerator;
import in.insurance.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	 private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanName() {
		return planRepo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
	CitizenPlan entity = new CitizenPlan();
	
	if(null!=request.getPlanName() && !"".equals(request.getPlanName())) 
		entity.setPlanName(request.getPlanName());

	if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) 
		entity.setPlanStatus(request.getPlanStatus());
	
	if(null!=request.getGender() && !"".equals(request.getGender())) 
		entity.setGender(request.getGender());
	
	if(null!=request.getStartDate() && !"".equals(request.getStartDate())) { 
		String startDate = request.getStartDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// Convert String to LocalDate
		LocalDate localDate = LocalDate.parse(startDate, formatter);
		
		entity.setStartDate(localDate);
	}
	if(null!=request.getEndDate() && !"".equals(request.getEndDate())) { 
		String endDate = request.getEndDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// Convert String to LocalDate
		LocalDate localDate = LocalDate.parse(endDate, formatter);
		
		entity.setEndDate(localDate);
	}
	
	return planRepo.findAll(Example.of(entity));
	}

	/*
	
	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		// It is for download in browser for email attatchment, i write below implementation 
		//Workbook workbook = new XSSFWorkbook(); // Suport xlsx extention
	
		Workbook workbook = new HSSFWorkbook(); // Suport xls extention
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amt");
		
		List<CitizenPlan> records = planRepo.findAll();
		
		int dataRowIndex=1;
		
		for(CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
							
			if(null != plan.getBenefitAmount()) 
				dataRow.createCell(5).setCellValue(plan.getStartDate()+"");
			else
				dataRow.createCell(5).setCellValue("N/A");
		
			if(null != plan.getBenefitAmount()) 
				dataRow.createCell(6).setCellValue(plan.getEndDate()+"");
			else
				dataRow.createCell(6).setCellValue("N/A");
			
			if(null != plan.getBenefitAmount()) 
			dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			else
				dataRow.createCell(7).setCellValue("N/A");
				
			dataRowIndex++;
			
		}

	ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);
		workbook.close();
		return true;
	}
			

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		// It is for download in browser for email attatchment, i write below implementation
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);
		
		Paragraph p = new Paragraph("Citizen Plans Info" , font );
		
		p.setAlignment(p.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(8);
		
		table.setSpacingBefore(5);
		
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amt");
		
		
		List<CitizenPlan> plans = planRepo.findAll();
		
		for(CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			
			if(null != plan.getStartDate())
			table.addCell(plan.getStartDate()+"");
			else
				table.addCell("N/A");
			
			if(null != plan.getEndDate())
			table.addCell(plan.getEndDate()+"");			
			else
				table.addCell("N/A");
			
			if(null != plan.getBenefitAmount())			
			table.addCell(String.valueOf(plan.getBenefitAmount()));
			else
				table.addCell("N/A");
		
		}

		document.add(table);
		document.close();
		
		return true;
	}
			*/
	
	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> plans = planRepo.findAll();
		
		File f = new File("plans.xls");
		
		excelGenerator.generate(response, plans,f);
		
		String subjec = "Your Report.....!!";
		String body = "<h1> Hi, I am generate a Report for you.  </h1>";
		String to = "fullstackjavanote@gmail.com";
		
		File file = new File("plans.xls");
		
		emailUtils.sendEmail(subjec, body, to, file);
		
		f.delete();
		
		return true;
	}
	
	
	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> plans = planRepo.findAll();
		
		File f = new File("plans.pdf");
		
		pdfGenerator.generate(response, plans,f);
		
		
		String subjec = "Your Report.....!!";
		String body = "<h1> Hi, I am generate a Report for you.  </h1>";
		String to = "fullstackjavanote@gmail.com";
		
		File file = new File("plans.pdf");
		
		emailUtils.sendEmail(subjec, body, to, file);
		
		f.delete();
		
		
		return true;
	}
	
	
	
	
	
}
