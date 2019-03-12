package com.beisi.common.comtool;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class POICell {
	
	public static String GetValue(Cell cell) {
		String value = "";
		if (null != cell) {
			switch (cell.getCellType()) {
			case BLANK:
				value = "";
				break;
			case BOOLEAN:
				value = Boolean.toString(cell.getBooleanCellValue());
				break;
			case ERROR:
				value = "";
				break;
			case FORMULA:
				cell.setCellType(CellType.STRING);
				value = cell.getStringCellValue();
				if (null != value && !"".equals(value)) {
					value = value.replaceAll("#N/A", "").trim();
				}
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = df.format(cell.getDateCellValue());
				} else {
					cell.setCellType(CellType.STRING);
					value = cell.getStringCellValue();
				}
				break;
			case STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value;
	}
	
	public static String GetValue(XSSFCell cell) {
		String value = "";
		if (null != cell) {
			switch (cell.getCellType()) {
			case BLANK:
				value = "";
				break;
			case BOOLEAN:
				value = Boolean.toString(cell.getBooleanCellValue());
				break;
			case ERROR:
				value = "";
				break;
			case FORMULA:
				cell.setCellType(CellType.STRING);
				value = cell.getStringCellValue();
				if (null != value && !"".equals(value)) {
					value = value.replaceAll("#N/A", "").trim();
				}
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = df.format(cell.getDateCellValue());
				} else {
					cell.setCellType(CellType.STRING);
					value = cell.getStringCellValue();
				}
				break;
			case STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value;
	}
}
