package net.nifoo.tij.jxl;

import java.io.File;
import java.net.URL;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlMain {

	public static void main(String[] args) throws Exception {
		JxlMain jxlMain = new JxlMain();
		jxlMain.load();
		
	}

	public void rewrite() throws Exception {
		String fileName = "系数档案.xls";
		URL url = JxlMain.class.getResource(fileName);
		File file = new File(url.toURI());

		Workbook book = null;
		try {
			book = Workbook.getWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		WritableWorkbook feedback = Workbook.createWorkbook(file, book);
		WritableSheet errorSheet = feedback.getSheet(0);

		int columns = errorSheet.getColumns();
		int rows = errorSheet.getRows();
		for (int i = 1; i < rows; i++) {
			errorSheet.addCell(new Label(columns, i, "aaaaa"));
		}

		feedback.write();

		feedback.close();
		book.close();
		System.out.println("finish");
	}
	
	public void load() throws Exception {
		String fileName = "系数档案_R.xls";
		URL url = JxlMain.class.getResource(fileName);
		File file = new File(url.toURI());

		Workbook book = null;
		try {
			book = Workbook.getWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Sheet sheet = book.getSheet(0);
		System.out.println("sheet: " + sheet.getName() );

		int columns = sheet.getColumns();
		int rows = sheet.getRows();
		
//		Cell cell = sheet.getCell("_XS_ln1");
		Cell cell = sheet.getCell("$H$3");
//		Cell cell = book.findCellByName("Sheet1!H4");
		System.out.println(cell.getContents());

		book.close();
		System.out.println("finish");
	}


}
