package org.sj.oaprj.home.service;

import java.util.List;

import org.sj.oaprj.entity.Menu;
import org.sj.oaprj.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceImpl {
	@Autowired
	private MenuRepository menuRepository;

	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Menu> findMenuAll() {
		List<Menu> menuList = menuRepository.findAll();
		return menuList;
	}
}
