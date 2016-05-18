package org.sj.oaprj.project.service;

import java.util.HashMap;
import java.util.Map;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.entity.Employee;
import org.sj.oaprj.project.domain.ProjectGroupUserUpdateDomain;
import org.sj.oaprj.project.entity.ProjectGroupUser;
import org.sj.oaprj.project.repository.ProjectGroupRepository;
import org.sj.oaprj.project.repository.ProjectGroupUserRepository;
import org.sj.oaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProjectGroupUserServiceImpl {
	@Autowired
	private ProjectGroupUserRepository projectGroupUserRepository;
	@Autowired
	private ProjectGroupRepository projectGroupRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ProjectGroupUser save(ProjectGroupUser entity) {
		return projectGroupUserRepository.save(entity);
	}
	public String save(ProjectGroupUserUpdateDomain domain) {
		ProjectGroupUser emp = null;
		ProjectGroupUser projectgroupuser = null;
		if(null == domain.getId()) {
			projectgroupuser = new ProjectGroupUser();
		} else {
			projectgroupuser = findOne(domain.getId());
		}

		projectgroupuser.setPost(domain.getPost());
		projectgroupuser.setRole(domain.getRole());
		if(null != domain.getProjectgroupId()) {
			projectgroupuser.setProjectgroup(projectGroupRepository.findOne(domain.getProjectgroupId()));
		}
		if(null != domain.getUserId()) {
			Employee user = userRepository.findOne(domain.getUserId());
			if(null != user) {
				projectgroupuser.setUser(user);
				projectgroupuser.setName(user.getName());
			}
		}
		emp = save(projectgroupuser);
		return Utils.isNull(emp) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}
	@Transactional(readOnly = true)
	public ProjectGroupUser findOne(Long id) {
		return projectGroupUserRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public ProjectGroupUserUpdateDomain findUpdate(Long id) {
		ProjectGroupUser projectgroupuser = findOne(id);
		ProjectGroupUserUpdateDomain d = new ProjectGroupUserUpdateDomain();
		if (null != projectgroupuser) {
			d.setId(projectgroupuser.getId());
			d.setName(projectgroupuser.getName());
			d.setRole(projectgroupuser.getRole());
			d.setPost(projectgroupuser.getPost());
			if(null != projectgroupuser.getUser()) {
				d.setUserId(projectgroupuser.getUser().getId());
			}
			if(null != projectgroupuser.getProjectgroup()) {
				d.setProjectgroupId(projectgroupuser.getProjectgroup().getId());
				d.setProjectgroupName(projectgroupuser.getProjectgroup().getName());
			}
		}
		return d;
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			ProjectGroupUser entity = projectGroupUserRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			projectGroupUserRepository.save(entity);
		}
	}

	@Transactional(readOnly = true)
	public Map<String, Object> findByFields(final String name, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<ProjectGroupUserUpdateDomain> page = null;
		if(Utils.isEmpty(name)) {
			page = projectGroupUserRepository.findAll(pageable, Constants.DELETE_FLAG_0);
		} else {
			page = projectGroupUserRepository.findAll(pageable, name, Constants.DELETE_FLAG_0);
		}
		result.put("total", page.getTotalElements());
		result.put("rows", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
