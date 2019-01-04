package com.yixin.framework.util.export;

import java.util.List;

public interface ExcelExportInterface {
	public List<String> addTitle(Integer row);
	public List<String> addHead(Integer row);
	public List<String> addRow(Integer row);
	public List<String> addFoot(Integer row);
}
