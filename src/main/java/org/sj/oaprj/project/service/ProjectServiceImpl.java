/**
 * 
 */
package org.sj.oaprj.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.home.service.DepartmentServiceImpl;
import org.sj.oaprj.project.core.ProjectState;
import org.sj.oaprj.project.domain.ProjectUpdateDomain;
import org.sj.oaprj.project.entity.Project;
import org.sj.oaprj.project.entity.ProjectDepart;
import org.sj.oaprj.project.entity.ProjectItem;
import org.sj.oaprj.project.entity.UnitProject;
import org.sj.oaprj.project.repository.ProjectDeptRepository;
import org.sj.oaprj.project.repository.ProjectGroupRepository;
import org.sj.oaprj.project.repository.ProjectItemRepository;
import org.sj.oaprj.project.repository.ProjectRepository;
import org.sj.oaprj.project.repository.UnitProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jack.Alexander
 *
 */
@Component
@Transactional
public class ProjectServiceImpl {
	private final static Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectDeptRepository projectDeptRepository;
	@Autowired
	private ProjectItemRepository projectItemRepository;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	private UnitProjectRepository unitProjectRepository;
	@Autowired
	private ProjectGroupRepository projectGroupRepository;
	@Autowired
	private ProjectExcelImportTools projectExcelImportTools;

	public String importProjectItemExcel(InputStream in, Long projectId, Long unitProjectId) throws IOException {
		String res = null;
		Project project = projectRepository.findOne(projectId);
		UnitProject unitProject = unitProjectRepository.findOne(unitProjectId);
		if(null == project) return res;
		LOG.info("Import excel.");
		List<ProjectDepart> deps = new ArrayList<ProjectDepart>();
		List<ProjectItem> items = new ArrayList<ProjectItem>();
		projectExcelImportTools.importExcel(in, project, unitProject, deps, items);
		if(deps.size() > 0 && items.size() > 0) {
			projectDeptRepository.save(deps);
			projectItemRepository.save(items);
		}
		return res;
	}
	
	/**
	 * Out Item all Name for validate input data
	 */
	@Transactional(readOnly = true)
	public void viewItemAllName() {
		Iterable<ProjectItem> list = projectItemRepository.findAll();
		Iterator<ProjectItem> it = list.iterator();
		while(it.hasNext()) {
			ProjectItem item = it.next();
			System.out.println(item.getName() + (null == item.getFeature()? "" : "\n" + item.getFeature())  + (null == item.getContent()? "" : "\n" + item.getContent()));
		}
	}

	public Project save(Project entity) {
		return projectRepository.save(entity);
	}

	public String save(ProjectUpdateDomain domain) {
		Project emp = null;
		Project project = null;
		if(null == domain.getId()) {
			project = new Project();
			project.setProjectno(domain.getProjectno());//生成一次不能编辑
			project.setState(ProjectState.CREATED.getIntValue());
		} else {
			project = findOne(domain.getId());
		}
		project.setName(domain.getName());
		project.setRemark(domain.getRemark());
		if(null != domain.getState()) {
			project.setState(domain.getState());
		}
		
		if(null != domain.getDepartmentId()) {
			project.setDepartment(departmentServiceImpl.findOne(domain.getDepartmentId()));
		}
		if(null != domain.getProjectgroupId()) {
			project.setProjectgroup(projectGroupRepository.findOne(domain.getProjectgroupId()));
		}
		emp = save(project);
		return Utils.isNull(emp) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}

	@Transactional(readOnly = true)
	public Project findOne(Long id) {
		return projectRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public ProjectUpdateDomain findUpdate(Long id) {
		Project project = findOne(id);
		ProjectUpdateDomain d = new ProjectUpdateDomain();
		if (null != project) {
			d.setId(project.getId());
			d.setName(project.getName());
			d.setProjectno(project.getProjectno());
			d.setRemark(project.getRemark());
			d.setState(project.getState());
			d.setCreatetime(project.getCreatetime());
			
			if(null != project.getDepartment()) {
				d.setDepartmentId(project.getDepartment().getId());
				d.setDepartmentName(project.getDepartment().getDeptName());
			}
			if(null != project.getProjectgroup()) {
				d.setProjectgroupId(project.getProjectgroup().getId());
				d.setProjectgroupName(project.getProjectgroup().getName());
			}
		}
		return d;
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			Project entity = projectRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			projectRepository.save(entity);
		}
	}

	@Transactional(readOnly = true)
	public Map<String, Object> findByFields(final String name, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<ProjectUpdateDomain> page = null;
		if(Utils.isEmpty(name)) {
			page = projectRepository.findAll(pageable, Constants.DELETE_FLAG_0);
		} else {
			page = projectRepository.findAll(pageable, "%" + name + "%", Constants.DELETE_FLAG_0);
		}
		result.put("total", page.getTotalElements());
		result.put("rows", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
