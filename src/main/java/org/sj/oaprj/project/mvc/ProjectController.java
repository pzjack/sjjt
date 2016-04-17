/**
 * 
 */
package org.sj.oaprj.project.mvc;

import java.io.IOException;

import org.sj.oaprj.project.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Jack.Alexander
 *
 */
@RestController
@RequestMapping(value = "/api/v1/project")
@Api(value = "工程项目管理API", description = "工程项目管理<br>@author Jack.Alexander")
public class ProjectController {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "导入广联达[分部分项目及清单子项]", response = Void.class, notes = "导入广联达[分部分项目及清单子项]<br>@author Jack.Alexander")
	public void upload(
			@ApiParam(value = "待上传Excel文件，暂时支持xls格式，也就是Excel 97-2003工作薄") @RequestParam(value = "file", required = true)
			MultipartFile file,
			@ApiParam(value = "工程项目ID") @RequestParam(value = "projectId", required = true)
			Long projectId) {
		try {
			projectServiceImpl.importExcel(file.getInputStream(), projectId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/console", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "控制台输出清单子项的所有名称", response = Void.class, notes = "控制台输出清单子项的所有名称<br>@author Jack.Alexander")
	public void upload() {
		projectServiceImpl.viewItemAllName();
	}
}
