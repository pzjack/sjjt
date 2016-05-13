package org.sj.oaprj.home.mvc;

import java.util.List;

import org.sj.oaprj.domain.RespTreeNode;
import org.sj.oaprj.entity.CodeType;
import org.sj.oaprj.home.service.CodeTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/codeType")
public class CodeTypeController {
	@Autowired
	private CodeTypeServiceImpl codeTypeServiceImpl;

	@ApiOperation(value = "代码分类新增画面", notes = "代码分类新增画面<br/>@auther dzhifang")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("system/codeTypeForm");
		modelAndView.addObject("model", new CodeType());
		return modelAndView;
	}
	
	@ApiOperation(value = "代码分类新增", notes = "代码分类新增<br/>@auther dzhifang")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(CodeType entity) {
		return codeTypeServiceImpl.save(entity);
	}

	@ApiOperation(value = "查询单个供应商信息", notes = "查询单个供应商信息<br/>@auther dzhifang")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Integer id) {
		CodeType codeType = codeTypeServiceImpl.findOne(id);
		ModelAndView modelAndView = new ModelAndView("system/codeTypeForm");
		modelAndView.addObject("model", codeType);
		return modelAndView;
	}
	
	@ApiOperation(value = "查询所有代码分类", notes = "查询所有代码分类<br/>@auther dzhifang")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<RespTreeNode> findAll() {
		return  codeTypeServiceImpl.findAll();
	}
}
