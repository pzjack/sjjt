/**
 * 
 */
package org.sj.oaprj.project.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.sj.oaprj.project.entity.UnitProject;
import org.sj.oaprj.project.repository.ProjectDeptRepository;
import org.sj.oaprj.project.repository.ProjectItemRepository;
import org.sj.oaprj.project.repository.ProjectRepository;
import org.sj.oaprj.project.repository.UnitProjectRepository;
import org.sj.oaprj.project.util.ProjectState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Jack.Alexander
 *
 */
@Component
public class ProjectServiceImpl {
	private final static Pattern p = Pattern.compile("^-?[0-9]+.[0-9]+");

	private final static char EXCEL_NO_VIEW_CHAR = 0X7F;
	private final static String EXCEL_NO_VIEW_STR = EXCEL_NO_VIEW_CHAR + "";
	private final static String EXCEL_ENTER_SIG = "\r\n";
	private final static char NEW_LINE = 0x0A;
	private final static String EXCEL_NEWLINE_SIG = "" + NEW_LINE;
	private final static String JAVA_ENTER_SIG = "\n";
	private final static String EXCEL_PRJ_FEATURE = "[项目特征]";
	private final static String EXCEL_PRJ_CONTENT = "[工程内容]";
//	private final static String EXCEL_PRJ_XUHAO = "序号";
	private final static String EXCEL_PRJ_XMMC = "项目名称";
	private final static String EXCEL_PRJ_FBXJ = "分部小计";
//	private final static String EXCEL_PRJ_BCFB = "补充分部";
	
	private final static Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectDeptRepository projectDeptRepository;
	@Autowired
	private ProjectItemRepository projectItemRepository;
//	@Autowired
//	private IndividualProjectRepository individualProjectRepository;
	@Autowired
	private UnitProjectRepository unitProjectRepository;

	/**
	 * 通过Web传入的excel文件及项目信息，初始化项目分部和子项清单
	 * @param in
	 * @param projectId
	 * @throws IOException
	 */
	public void importExcel(InputStream in, Long projectId, Long unitProjectId) throws IOException {
		Project project = projectRepository.findOne(projectId);
		UnitProject unitProject = unitProjectRepository.findOne(unitProjectId);
		if(null == project) return;
		LOG.info("Import excel.");
		readHssfBook(in, project, unitProject);
	}
	
	/**
	 * Out Item all Name for validate input data
	 */
	public void viewItemAllName() {
		Iterable<ProjectItem> list = projectItemRepository.findAll();
		Iterator<ProjectItem> it = list.iterator();
		while(it.hasNext()) {
			ProjectItem item = it.next();
			System.out.println(item.getName() + (null == item.getFeature()? "" : "\n" + item.getFeature())  + (null == item.getContent()? "" : "\n" + item.getContent()));
		}
		
	}
	
	/**
	 * 解析Excel 2003及之前格式的广联达报表数据，初始化对应的项目分部分工程
	 * 并将项目、项目分部分工程、清单子项建立相应的关系
	 * @param project
	 * @param unitProject
	 * @throws IOException
	 */
	private void readHssfBook(InputStream in, Project project, UnitProject unitProject) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(3);
		if(null == hssfSheet) {
			if(null != hssfWorkbook) {
				hssfWorkbook.close();
			}
			return;
		}
		Date createDate = new Date();
		List<ProjectDepart> deps = new ArrayList<ProjectDepart>();
		List<ProjectItem> items = new ArrayList<ProjectItem>();
		ProjectDepart dep = new ProjectDepart();
//		boolean prePage = false;
		String ssname = null, sitemno = null, sname = null;//记录最多2级的分部分项名称和一级编号
		int sj = 0;//计数分部分项级数
		boolean fbcreate = false, sscreate = false;//分部分项创建标志
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				HSSFCell cell = hssfRow.getCell(0);
				String no = getCellValue(cell);//序号
				cell = hssfRow.getCell(1);
				String itemno = getCellValue(cell);//清单子项编号
				cell = hssfRow.getCell(2);
				String nameall = getCellValue(cell);//名称合体
				if(null == nameall || nameall.trim().isEmpty()) {
					continue;
				}
				if(EXCEL_PRJ_XMMC.equals(nameall.trim()) || EXCEL_PRJ_FBXJ.equals(nameall.trim()) ) {
					fbcreate = false;
					continue;
				}
				cell = hssfRow.getCell(3);
				String unit = getCellValue(cell);//计量单位
				cell = hssfRow.getCell(4);
				String projNum = getCellValue(cell);//工程数量
				cell = hssfRow.getCell(5);
				String unitPrice = getCellValue(cell);//综合单价
				cell = hssfRow.getCell(6);
				String totalPrice = getCellValue(cell);//合价
				if(StringUtils.isEmpty(no) &&  !StringUtils.isEmpty(nameall)) {
					sitemno = null;
					fbcreate = false;
//					if(EXCEL_PRJ_BCFB.equals(nameall.trim())) {
//						sscreate = false;
//					}
					if(0 == sj) {
						if(sscreate && null == ssname) {
							ssname = nameall;
						} else {
							sname = nameall;
						}
					}
					if(1 == sj) {
						ssname = sname;
						sname = nameall;
						sscreate = false;
					}
					if(null != itemno && !itemno.trim().isEmpty()) {
						sitemno = itemno;
					}
					sj++;
				} else {
					/*if(!StringUtils.isEmpty(no) && (no.indexOf(EXCEL_PRJ_XUHAO) >= 0)) {
						if(!items.isEmpty()) {
							prePage = true;
						}
						continue;
					}
					if(prePage && !StringUtils.isEmpty(nameall)) {//这部分逻辑处理跨页数据整合的问题，新的excel中不存在这种情况
						if(StringUtils.isEmpty(no)) {
							prePage = false;
							if(items.isEmpty()) {
								continue;
							}
							ProjectItem item = items.get(items.size() - 1);
							int p1=-1;
							p1 = nameall.indexOf(EXCEL_ENTER_SIG);
							if(p1 < 0) {
								nameall = nameall.trim();
								if(!StringUtils.isEmpty(item.getContent()) && !StringUtils.isEmpty(item.getFeature())) {
									item.setContent(item.getContent() + JAVA_ENTER_SIG + nameall);
								} else {
									if(nameall.startsWith("1.")) {
										item.setContent(item.getContent() + JAVA_ENTER_SIG + nameall);
									} else {
										item.setFeature(item.getFeature() + JAVA_ENTER_SIG + nameall);
									}
								}
								continue;
							}
							appendPrePagePartData(item, nameall, p1);
							continue;
						} else {
							prePage = false;
						}
					}*/
					if(!StringUtils.isEmpty(no) && (isNumeric(no))) {//单行数据页内处理逻辑，首次遇到清单子项
						if(sj > 0 &&  !fbcreate) {
							dep = new ProjectDepart();
							if(null != sitemno)
								dep.setDepartno(sitemno);
							dep.setName(sname);
							if(2 == sj) {
								sscreate = true;
							}
							if(sscreate) {
								dep.setDepartSuper(ssname);
							}
							dep.setProject(project);
							if(null != unitProject) {
								dep.setUnitProject(unitProject);
								dep.setCompleteName(unitProject.getCompleteName());
							}
							dep.setCreatetime(createDate);
							dep.setState(ProjectState.CREATE.getState());
							deps.add(dep);
							fbcreate = true;
							sj = 0;
						}
						
						ProjectItem item = new ProjectItem();
						item.setProjectDepart(dep);
						item.setItemno(itemno);//清单子项编号
						item.setCreatetime(createDate);
						item.setState(ProjectState.CREATE.getState());
						
						if(!StringUtils.isEmpty(no)) {//序号
							int p = no.indexOf(".");
							if(p > 0) {
								item.setPrjno(no.substring(0, p));
							} else {
								item.setPrjno(no);
							}
						}
						
						if(!StringUtils.isEmpty(unit)) {//计量单位
							item.setUnit(unit.trim());
						}
						if(!StringUtils.isEmpty(projNum)) {//工程数量
							item.setTotalproject(new BigDecimal(projNum.trim()));
						}
						if(!StringUtils.isEmpty(unitPrice)) {//综合单价
							item.setUnitprice(new BigDecimal(unitPrice.trim()));
						}
						if(!StringUtils.isEmpty(totalPrice)) {//合价
							item.setSumofbusiness(new BigDecimal(totalPrice.trim()));
						}
				
						diliveItemNameNormal(item, nameall);
						items.add(item);
					}
				}
			}
		}
		hssfWorkbook.close();
		
//		System.out.println(deps.size() + "\t" + items.size());
		projectDeptRepository.save(deps);
		projectItemRepository.save(items);
	}
	
	/**
	 * 处理不跨页的正常情况
	 * 将名称、项目特征、工程内容分离出来，同时去除excel中的不方便处理的字符
	 * @param item
	 * @param c2
	 */
	private void diliveItemNameNormal(ProjectItem item, String c2) {
		int p1=-1,p2 = -1;
		p1 = c2.indexOf(EXCEL_ENTER_SIG);
		String tem = null;
		if(p1 > 0) {
			tem = c2.substring(0, p1);
		} else {
			tem = c2;
		}
		if(null != tem) {
			tem = tem.trim();
		} else {
			return;
		}

		tem = tem.replaceAll(EXCEL_NO_VIEW_STR, "");//DEL字符处理
		tem = tem.replaceAll(EXCEL_NEWLINE_SIG, "");//
		if(null != tem) {
			tem = tem.trim();
		}
		item.setName(tem);
		
		p2 = p1;
		StringBuilder xtsb = new StringBuilder();
		StringBuilder gnsb = new StringBuilder();
		boolean xt = false,gn = false;
		while(p2 > 0) {
			p1 = c2.indexOf(EXCEL_ENTER_SIG, p2 + 2);
			if(p1 > 0) {
				tem = c2.substring(p2 + 2, p1);
			} else {
				tem = c2.substring(p2 + 2);
			}

			tem = tem.replaceAll(EXCEL_NO_VIEW_STR, "");//DEL字符处理
			tem = tem.replaceAll(EXCEL_NEWLINE_SIG, "");//
			p2 = p1;
			if(null != tem) {
				tem = tem.trim();
				if(tem.indexOf(EXCEL_PRJ_FEATURE) >= 0) {
					xt = true;
					gn = false;
				}
				if(tem.indexOf(EXCEL_PRJ_CONTENT) >= 0) {
					xt = false;
					gn = true;
				}
				if(xt) {
					xtsb.append(tem).append(JAVA_ENTER_SIG);
				}
				if(gn) {
					gnsb.append(tem).append(JAVA_ENTER_SIG);
				}
			}
		}
		if(xtsb.length() > 0)
		item.setFeature(xtsb.toString().substring(0, xtsb.length() -1));
		if(gnsb.length() > 0)
		item.setContent(gnsb.toString().substring(0, gnsb.length() - 1));
	}
	/**
	 * 处理跨页的情况，将下一页的部分数据并入最近的清单子项中
	 * 将名称、项目特征、工程内容分离出来，同时去除excel中的不方便处理的字符
	 * @param item
	 * @param c2
	 * @param p1
	 */
	/*
	private void appendPrePagePartData(ProjectItem item, String c2, int p1) {
		StringBuilder xtsb = new StringBuilder();
		StringBuilder gnsb = new StringBuilder();
		boolean xt = false,gn = false;
		String tem = c2.substring(0, p1);
		int p2 = p1;
		while(p2 > 0) {
			if(null != tem) {
				if(tem.indexOf(EXCEL_PRJ_FEATURE) >= 0) {
					xt = true;
					gn = false;
				}
				if(tem.indexOf(EXCEL_PRJ_CONTENT) >= 0) {
					xt = false;
					gn = true;
				}
				tem = tem.trim();
			}
			if(!xt && ! gn) {
				if(!StringUtils.isEmpty(item.getContent()) && !StringUtils.isEmpty(item.getFeature())) {
					xt = false;
					gn = true;
				} else {
					xt = true;
					gn = false;
				}
			}
			if(xt) {
				xtsb.append(tem).append(JAVA_ENTER_SIG);
			}
			if(gn) {
				gnsb.append(tem).append(JAVA_ENTER_SIG);
			}

			p1 = c2.indexOf(EXCEL_ENTER_SIG, p2 + 2);
			if(p1 > 0) {
				tem = c2.substring(p2 + 2, p1);
			} else {
				tem = c2.substring(p2 + 2);
			}
			if(p1 < 0) {
				if(xt) {
					xtsb.append(tem.trim()).append(JAVA_ENTER_SIG);
				}
				if(gn) {
					gnsb.append(tem.trim()).append(JAVA_ENTER_SIG);
				}
			}
			p2 = p1;
		}
		if(xtsb.length() != 0) {
			if(StringUtils.isEmpty(item.getFeature())) {
				item.setFeature(xtsb.substring(0, xtsb.length() - 1));
			} else {
				item.setFeature(item.getFeature() + JAVA_ENTER_SIG + xtsb.substring(0, xtsb.length() - 1));
			}
		}
		if(gnsb.length() != 0) {
			if(StringUtils.isEmpty(item.getContent())) {
				item.setContent(gnsb.substring(0, gnsb.length() - 1));
			} else {
				item.setContent(item.getContent() + JAVA_ENTER_SIG + gnsb.substring(0, gnsb.length() - 1));
			}
		}
	}*/
	
	public boolean isNumeric(String str) {
		Matcher isNum = p.matcher(str);
		if(isNum.matches()) {
			return true;
		}
		return false;
	}
	
	public String getCellValue(HSSFCell cell) {
		if(null == cell) return null;
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
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ProjectServiceImpl t = new ProjectServiceImpl();
		t.readHssfBook(new BufferedInputStream(new FileInputStream("f:/陕建翠园锦绣9#楼一般土建工程2.xls")), null, null);
//		String a = "5、2:8灰土分层夯实，宽度>800（另列）";
//		for(int i =0 ;i < a.length(); i++) {
//			char c = a.charAt(i);
//			System.out.println(c + "\t" + (0 + c));
//		}
//		t.readXLSX("f:/test.xls");
	}
}
