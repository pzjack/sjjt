package org.sj.oaprj.home.mvc;

import java.util.List;
import java.util.Map;

import org.sj.oaprj.entity.CodeData;
import org.sj.oaprj.home.service.CodeDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/codeData")
public class CodeDataController {
	@Autowired
	private CodeDataServiceImpl codeDataServiceImpl;

	@ApiOperation(value = "基础数据列表", notes = "基础数据列表<br/>@auther dzhifang")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "organization/codeDataList";
	}

	@ApiOperation(value = "基础数据列表", notes = "基础数据列表<br/>@auther dzhifang")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(CodeData org, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = codeDataServiceImpl.findByFields(org, new PageRequest(pageIndex, pageSize));
		return result;
	}

	@ApiOperation(value = "基础数据新增画面", notes = "基础数据新增画面<br/>@auther dzhifang")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("organization/codeDataForm");
		modelAndView.addObject("codeData", new CodeData());
		return modelAndView;
	}

	@ApiOperation(value = "基础数据信息新增", notes = "基础数据信息新增<br/>@auther dzhifang")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody int save(CodeData entity) {
		return codeDataServiceImpl.save(entity);
	}

	@ApiOperation(value = "查询单个基础数据信息", notes = "查询单个基础数据信息<br/>@auther dzhifang")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		CodeData codeData = codeDataServiceImpl.findOne(id);
		ModelAndView modelAndView = new ModelAndView("organization/codeDataForm");
		modelAndView.addObject("codeData", codeData);
		return modelAndView;
	}

	@ApiOperation(value = "基础数据信息删除", notes = "基础数据信息删除<br/>@auther dzhifang")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		codeDataServiceImpl.delete(idArray);
	}

	@ApiOperation(value = "根据数据代码查询数据信息", notes = "根据数据代码查询数据信息<br/>@auther dzhifang")
	@RequestMapping(value = "/findByCodeType", method = RequestMethod.GET)
	public @ResponseBody List<CodeData> findByCodeType(Integer codeType) {
		List<CodeData> codeDataList = codeDataServiceImpl.findByCodeType(codeType);
		return codeDataList;
	}
}
