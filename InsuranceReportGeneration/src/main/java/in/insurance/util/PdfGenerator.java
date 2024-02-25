package in.insurance.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.insurance.entity.CitizenPlan;

@Component
public class PdfGenerator {
	
	
	public void generate(HttpServletResponse response , List<CitizenPlan> records, File f ) throws Exception {
		
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		
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
						
		for(CitizenPlan plan : records) {
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
		

		
		
		
		
		
		
		
		
	}
	
}
