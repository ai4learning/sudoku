package com.goldfish.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.PushbackInputStream;

public class XLSAnalytic {
	public static Sheet getSheet(MultipartFile excl, int pageAt) {
		Sheet sheet = null;
		try {
			Workbook workbook = null;
			InputStream inputStream = excl.getInputStream();
			if (!inputStream.markSupported()) {
				inputStream = new PushbackInputStream(inputStream,
						10);
			}
			if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {// 2003
				workbook = new HSSFWorkbook(inputStream);
			}
//			if (POIXMLDocument.hasOOXMLHeader(inputStream)) {// 2007
//				workbook = new XSSFWorkbook(OPCPackage.open(inputStream));
//			}
			sheet = workbook.getSheetAt(pageAt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public static String getValue(Cell cell) {
        if(cell == null){
            return null;
        }
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
