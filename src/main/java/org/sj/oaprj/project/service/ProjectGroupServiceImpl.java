package org.sj.oaprj.project.service;

import java.util.HashMap;
import java.util.Map;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.home.service.DepartmentServiceImpl;
import org.sj.oaprj.project.domain.ProjectGroupUpdateDomain;
import org.sj.oaprj.project.entity.ProjectGroup;
import org.sj.oaprj.project.repository.ProjectGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProjectGroupServiceImpl {
	@Autowired
	private ProjectGroupRepository projectGroupRepository;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	public ProjectGroup save(ProjectGroup entity) {
		return projectGroupRepository.save(entity);
	}
	public String save(ProjectGroupUpdateDomain domain) {
		ProjectGroup emp = null;
		ProjectGroup projectgroup = null;
		if(null == domain.getId()) {
			projectgroup = new ProjectGroup();
		} else {
			projectgroup = findOne(domain.getId());
		}
		projectgroup.setName(domain.getName());
		projectgroup.setRemark(domain.getRemark());
		if(null != domain.getDepartmentId()) {
			projectgroup.setDepartment(departmentServiceImpl.findOne(domain.getDepartmentId()));
		}
		emp = save(projectgroup);
		return Utils.isNull(emp) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}
	@Transactional(readOnly = true)
	public ProjectGroup findOne(Long id) {
		return projectGroupRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public ProjectGroupUpdateDomain findUpdate(Long id) {
		ProjectGroup projectgroup = findOne(id);
		ProjectGroupUpdateDomain d = new ProjectGroupUpdateDomain();
		if (null != projectgroup) {
			d.setId(projectgroup.getId());
			d.setName(projectgroup.getName());
			d.setRemark(projectgroup.getRemark());
			if(null != projectgroup.getDepartment()) {
				d.setDepartmentId(projectgroup.getDepartment().getId());
			}
		}
//		d.setDeps(departmentServiceImpl.findAll());
		return d;
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			ProjectGroup entity = projectGroupRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			projectGroupRepository.save(entity);
		}
	}

	@Transactional(readOnly = true)
	public Map<String, Object> findByFields(final String name, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<ProjectGroupUpdateDomain> page = null;
		if(Utils.isEmpty(name)) {
			page = projectGroupRepository.findAll(pageable, Constants.DELETE_FLAG_0);
		} else {
			page = projectGroupRepository.findAll(pageable, name, Constants.DELETE_FLAG_0);
		}
		result.put("total", page.getTotalElements());
		result.put("rows", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
