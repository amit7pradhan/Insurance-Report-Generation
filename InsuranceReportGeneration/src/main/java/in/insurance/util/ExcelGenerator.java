package in.insurance.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.insurance.entity.CitizenPlan;

@Component
public class ExcelGenerator {

	public void generate(HttpServletResponse response , List<CitizenPlan> records, File file) throws Exception {
				
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

		
		 FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		 fos.close();
		 
	ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);		
		workbook.close();
		
	}
	
}
