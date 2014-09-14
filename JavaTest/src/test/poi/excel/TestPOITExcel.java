package test.poi.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class TestPOITExcel {

	public static void main(String[] args) {
		testCreateExcel();
	}
	
	private static void testCreateExcel(){
		String pathname = "workbook.xls";
		File file = new File(pathname);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = null;
			Cell cell = null;
			
			CellStyle cs = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			//CellStyle cs3 = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			
			Font f = wb.createFont();
			Font f2 = wb.createFont();
			
			f2.setFontHeightInPoints((short)10);
			f2.setColor(Font.COLOR_RED);
			f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			f2.setStrikeout(true);
			
			cs.setFont(f);
			cs.setDataFormat(df.getFormat("#.##0.0"));
			
			cs2.setBorderBottom(CellStyle.BORDER_THIN);
			cs2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
			cs2.setFont(f2);
			
			wb.setSheetName(0, "testSheet");
			
			int rownum;
			for (rownum = 0; rownum < 30; rownum++) {
				row = sheet.createRow(rownum);
				if (rownum%2 == 0) {
					row.setHeight((short)0x249);
				}
				for (short cellnum = 0; cellnum < 10; cellnum+=2) {
					cell = row.createCell(cellnum);
					cell.setCellValue(rownum*1000+cellnum);
					cell = row.createCell(cellnum+1);
					if (rownum%2 ==0) {
						cell.setCellValue("rownum%2 ==0");
					}else{
						cell.setCellStyle(cs2);
						cell.setCellValue("rownum%2 !=0");
					}
				}
			}
			rownum++;
			rownum++;
			row = sheet.createRow(rownum);
			sheet = wb.createSheet();
			wb.setSheetName(1, "sheetsecond");
			wb.setActiveSheet(1);
			//wb.removeSheetAt(1);
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
