/**
 * 
 */
package org.sj.oaprj.project.mvc;

import java.io.IOException;

import org.sj.oaprj.project.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhen.pan
 *
 */
@RestController
@RequestMapping(value = "/api/v1/project")
public class ProjectController {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	@RequestMapping("/upload")
	public void upload(MultipartFile file, Long projectId) {
		try {
			projectServiceImpl.importExcel(file.getInputStream(), projectId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
}
