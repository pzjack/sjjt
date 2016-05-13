package org.sj.oaprj.project.mvc;

import java.util.Map;

import org.sj.oaprj.entity.Role;
import org.sj.oaprj.project.entity.ConstructionUnitDaily;
import org.sj.oaprj.project.service.ConstructionDailyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/project/daily/contruct")
@Api(value = "施工单位日报API", description = "施工单位日报<br>@author Jack.Alexander")
public class ConstructionDailyController {
	@Autowired
	private ConstructionDailyServiceImpl constructionDailyService;

	@ApiOperation(value = "施工单位日报页面", notes = "施工单位日报页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "projects/daily/ConstructionList";
	}
	
	@ApiOperation(value = "施工单位日报列表", notes = "施工单位日报<br/>@auther dzhifang")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String roleName, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = constructionDailyService.findByFields(roleName, buildPageRequest(pageIndex, pageSize));
		return result;
	}
	
	private PageRequest buildPageRequest(final int page, final int size) {
        return new PageRequest(page - 1, size);
    }

	@ApiOperation(value = "施工单位日报画面", notes = "施工单位日报画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("projects/daily/ConstructionForm");
		modelAndView.addObject("role", new Role());
		return modelAndView;
	}

	@ApiOperation(value = "施工单位日报新增", notes = "施工单位日报新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody int save(ConstructionUnitDaily entity) {
		return constructionDailyService.save(entity);
	}

	@ApiOperation(value = "查询施工单位日报信息", notes = "查询施工单位日报信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		ConstructionUnitDaily entity = constructionDailyService.findOne(id);
		ModelAndView modelAndView = new ModelAndView("projects/daily/ConstructionForm");
		modelAndView.addObject("entity", entity);
		return modelAndView;
	}

	@ApiOperation(value = "施工单位日报删除", notes = "施工单位日报删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		constructionDailyService.delete(idArray);
	}
}
