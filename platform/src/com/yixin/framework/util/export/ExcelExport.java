package com.yixin.framework.util.export;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelExport {

	public ExcelExport() {

	}

	public static void addObject(List<String> ls, Object o) {
		if (o == null) {
			ls.add("");
		} else {
			ls.add(o.toString());
		}
	}

	public static List<String> Array2List(String[] ss) {
		List<String> ls = new ArrayList<String>();
		for (int i = 0; i < ss.length; i++) {
			ls.add(ss[i]);
		}
		return ls;
	}

	private Boolean addRow(WritableSheet sheet, List<String> ls, Integer row) {
		jxl.write.Label label;
		if (ls != null) {
			Integer colIdx = 0;
			Iterator<String> it = ls.iterator();
			while (it.hasNext()) {
				String s = it.next();
				label = new jxl.write.Label(colIdx, row, s);
				try {
					sheet.addCell(label);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
				colIdx++;
			}
			return true;
		} else {
			return false;
		}

	}

	public boolean buileExcelStream(OutputStream os,
			ExcelExportInterface callBack) {
		WritableWorkbook workbook;
		Integer rowIdx = 0;

		List<String> ls;

		try {
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			ls = callBack.addTitle(rowIdx);
			addRow(sheet, ls, rowIdx);
			ls = callBack.addHead(rowIdx);
			rowIdx++;
			addRow(sheet, ls, rowIdx);
			while (true) {
				rowIdx++;
				ls = callBack.addRow(rowIdx);
				if (!addRow(sheet, ls, rowIdx)) {
					break;
				}
			}
			rowIdx++;
			ls = callBack.addFoot(0);
			addRow(sheet, ls, rowIdx);

			workbook.write();
			workbook.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
