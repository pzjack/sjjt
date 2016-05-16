/**
 * 
 */
package org.sj.oaprj.project.mvc;

import java.io.IOException;
import java.util.Map;

import org.sj.oaprj.project.domain.ProjectUpdateDomain;
import org.sj.oaprj.project.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Jack.Alexander
 *
 */
@Controller
@RequestMapping(value = "/projects/mng")
@Api(value = "工程项目管理API", description = "工程项目管理<br>@author Jack.Alexander")
public class ProjectController {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "导入广联达[分部分项目及清单子项]", response = Void.class, notes = "导入广联达[分部分项目及清单子项]<br>@author Jack.Alexander")
	public String upload(
			@ApiParam(value = "待上传Excel文件，暂时支持xls格式，也就是Excel 97-2003工作薄") @RequestParam(value = "file", required = true)
			MultipartFile file,
			@ApiParam(value = "工程项目ID") @RequestParam(value = "projectId", required = false)
			Long projectId,
			@ApiParam(value = "单位工程ID") @RequestParam(value = "unitprojectId", required = true)
			Long unitprojectId) {
		
		try {
			projectServiceImpl.importProjectItemExcel(file.getInputStream(), projectId, unitprojectId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "projects/project/uploadProjectDepart";
	}
	@RequestMapping(value = "/console", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "控制台输出清单子项的所有名称", response = Void.class, notes = "控制台输出清单子项的所有名称<br>@author Jack.Alexander")
	public void upload() {
		projectServiceImpl.viewItemAllName();
	}
	
	@RequestMapping(value = "/viewupload", method = RequestMethod.GET)
	public String uploadView() {
		return "projects/project/uploadProjectDepart";
	}
	
	@ApiOperation(value = "项目组列表页面", notes = "项目组列表页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "projects/project/projectList";
	}
	
	@ApiOperation(value = "项目组列表", notes = "项目组列表<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String name,  Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = projectServiceImpl.findByFields(name, buildPageRequest(pageIndex, pageSize));
		return result;
	}
	
	private PageRequest buildPageRequest(final int page, final int size) {
        return new PageRequest(page - 1, size);
    }

	@ApiOperation(value = "项目组新增画面", notes = "项目组新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("projects/project/projectForm");
		ProjectUpdateDomain pd = new ProjectUpdateDomain();
		modelAndView.addObject("project", pd);
		return modelAndView;
	}

	@ApiOperation(value = "项目组信息新增", notes = "项目组信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(ProjectUpdateDomain domain) {
		return projectServiceImpl.save(domain);
	}

	@ApiOperation(value = "查询单个项目组信息", notes = "查询单个项目组信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		ProjectUpdateDomain pd = projectServiceImpl.findUpdate(id);
		ModelAndView modelAndView = new ModelAndView("projects/project/projectForm");
		modelAndView.addObject("project", pd);
		return modelAndView;
	}

	@ApiOperation(value = "项目组信息删除", notes = "项目组信息删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		projectServiceImpl.delete(idArray);
	}
}
