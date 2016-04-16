/**
 * 
 */
package org.sj.oaprj.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.sj.oaprj.project.entity.Project;
import org.sj.oaprj.project.entity.ProjectDepart;
import org.sj.oaprj.project.entity.ProjectItem;
import org.sj.oaprj.project.repository.ProjectDeptRepository;
import org.sj.oaprj.project.repository.ProjectItemRepository;
import org.sj.oaprj.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zhen.pan
 *
 */
@Component
public class ProjectServiceImpl {
	Pattern p = Pattern.compile("^-?[0-9]+.[0-9]+");
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectDeptRepository projectDeptRepository;
	@Autowired
	private ProjectItemRepository projectItemRepository;

	public void importExcel(InputStream in, Long projectId) throws IOException {
		Project project = projectRepository.findOne(projectId);
		if(null == project) return;
		readHssfBook(in, project);
	}
	
	public void readHssfBook(InputStream in, Project project) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		if(null == hssfSheet) {
			if(null != hssfWorkbook) {
				hssfWorkbook.close();
			}
			return;
		}
		List<ProjectDepart> deps = new ArrayList<ProjectDepart>();
		List<ProjectItem> items = new ArrayList<ProjectItem>();
		ProjectDepart dep = new ProjectDepart();
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				HSSFCell cell = hssfRow.getCell(0);
				String c0 = getCellValue(cell);
				cell = hssfRow.getCell(1);
				String c1 = getCellValue(cell);
				cell = hssfRow.getCell(2);
				String c2 = getCellValue(cell);
				cell = hssfRow.getCell(3);
//				String c3 = getCellValue(cell);
				cell = hssfRow.getCell(4);
				String c4 = getCellValue(cell);
				cell = hssfRow.getCell(5);
				String c5 = getCellValue(cell);
				cell = hssfRow.getCell(6);
				String c6 = getCellValue(cell);
				cell = hssfRow.getCell(7);
//				String c7 = getCellValue(cell);
				cell = hssfRow.getCell(8);
				String c8 = getCellValue(cell);
				if(StringUtils.isEmpty(c0) && !StringUtils.isEmpty(c1) && !StringUtils.isEmpty(c2)) {
					dep = new ProjectDepart();
					dep.setDepartno(c1);
					dep.setName(c2);
					dep.setProject(project);
					deps.add(dep);
				} else {
					if(!StringUtils.isEmpty(c0) && (isNumeric(c0))) {
						ProjectItem item = new ProjectItem();
						item.setProjectDepart(dep);
						item.setItemno(c1);
				
						int p1,p2 = -1;
						p1 = c2.indexOf("\r\n");
						if(p1 > 0) {
							item.setName(c2.substring(0, p1).trim());
						} else {
							item.setName(c2.trim());
						}
						p2 = p1;
						StringBuilder xtsb = new StringBuilder();
						StringBuilder gnsb = new StringBuilder();
						boolean xt = false,gn = false;
						while(p2 > 0) {
							p1 = c2.indexOf("\r\n", p2 + 2);
							String tem = null;
							if(p1 > 0) {
								tem = c2.substring(p2 + 2, p1);
							} else {
								tem = c2.substring(p2 + 2);
							}
							p2 = p1;
							if(null != tem && "[项目特征]".equals(tem)) {
								xt = true;
								gn = false;
								continue;
							}
							if(null != tem && "[工程内容]".equals(tem)) {
								xt = false;
								gn = true;
								continue;
							}
							if(xt) {
								xtsb.append(tem).append("\n");
							}
							if(gn) {
								gnsb.append(tem).append("\n");
							}
						}
						if(xtsb.length() > 0)
						item.setFeature(xtsb.toString().substring(0, xtsb.length() -1));
						if(gnsb.length() > 0)
						item.setContent(gnsb.toString().substring(0, gnsb.length() - 1));
						
						if(!StringUtils.isEmpty(c4)) {
							item.setUnit(c4.trim());
						}
						if(!StringUtils.isEmpty(c5)) {
							item.setTotalproject(new BigDecimal(c5.trim()));
						}
						if(!StringUtils.isEmpty(c6)) {
							item.setUnitprice(new BigDecimal(c6.trim()));
						}
						if(!StringUtils.isEmpty(c8)) {
							item.setSumofbusiness(new BigDecimal(c8.trim()));
						}
						items.add(item);
					}
				}
			}
		}
		hssfWorkbook.close();
		
		projectDeptRepository.save(deps);
		projectItemRepository.save(items);
//		System.out.println(deps.size());
//		System.out.println(items.size());
		
	}
	
	public boolean isNumeric(String str) {
		Matcher isNum = p.matcher(str);
		if(isNum.matches()) {
			return true;
		}
		return false;
	}
	
	public String getCellValue(HSSFCell cell) {
		if(HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return cell.getStringCellValue();
		} else if(HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if(HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return cell.getStringCellValue();
		}
	}
	
//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		ProjectServiceImpl t = new ProjectServiceImpl();
//		t.readHssfBook(new BufferedInputStream(new FileInputStream("f:/test2.xls")));
////		t.readXLSX("f:/test.xls");
//	}
}
