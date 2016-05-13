package org.sj.oaprj.home.mvc;

import java.util.Map;

import org.sj.oaprj.entity.Supplier;
import org.sj.oaprj.home.service.SupplierServiceImpl;
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
@RequestMapping(value = "/supplier")
public class SupplierController {
	@Autowired
	private SupplierServiceImpl supplierServiceImpl;

	@ApiOperation(value = "供应商列表", notes = "供应商列表<br/>@auther dzhifang")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "organization/supplierList";
	}

	@ApiOperation(value = "供应商列表", notes = "供应商列表<br/>@auther dzhifang")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(Supplier org, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = supplierServiceImpl.findByFields(org, new PageRequest(pageIndex, pageSize));
		return result;
	}

	@ApiOperation(value = "供应商新增画面", notes = "供应商新增画面<br/>@auther dzhifang")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("organization/supplierForm");
		modelAndView.addObject("supplier", new Supplier());
		return modelAndView;
	}

	@ApiOperation(value = "供应商信息新增", notes = "供应商信息新增<br/>@auther dzhifang")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(Supplier entity) {
		return supplierServiceImpl.save(entity);
	}

	@ApiOperation(value = "查询单个供应商信息", notes = "查询单个供应商信息<br/>@auther dzhifang")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		Supplier supplier = supplierServiceImpl.findOne(id);
		ModelAndView modelAndView = new ModelAndView("organization/supplierForm");
		modelAndView.addObject("supplier", supplier);
		return modelAndView;
	}

	@ApiOperation(value = "供应商信息删除", notes = "供应商信息删除<br/>@auther dzhifang")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		supplierServiceImpl.delete(idArray);
	}
}
