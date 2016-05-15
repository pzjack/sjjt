package org.sj.oaprj.home.service;

import java.util.ArrayList;
import java.util.List;

import org.sj.oaprj.entity.Menu;
import org.sj.oaprj.entity.RoleMenu;
import org.sj.oaprj.repository.MenuRepository;
import org.sj.oaprj.repository.RoleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceImpl {
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RoleMenuRepository roleMenuRepository;

	/**
	 * 查询所有菜单
	 * 
	 * @return
	 */
	public List<Menu> findMenuAll() {
		List<Menu> menuList = menuRepository.findAll();
		return menuList;
	}

	public Menu findMenuByRoleId(Long roleId) {
		List<Menu> tempMenuList = new ArrayList<Menu>();
		// 查询菜单列表
		List<Menu> menuList = menuRepository.findAll();

		// 查询角色菜单列表
		List<RoleMenu> roleMenuList = roleMenuRepository.findByRoleId(roleId);
		// 通过角色菜单列表过滤菜单信息
		if (roleMenuList != null && roleMenuList.size() > 0) {
			for (Menu menu : menuList) {
				for (RoleMenu roleMenu : roleMenuList) {
					if (menu.getId().equals(roleMenu.getMenuId())) {
						tempMenuList.add(menu);
					}
				}
			}
			List<Menu> authedMenuList = new ArrayList<Menu>();
			for (Menu menu : tempMenuList) {
				getParentMenu(menu, menuList, authedMenuList);
			}
			menuList = authedMenuList;
		}
		return CreateTreeNodes(menuList);
	}

	/**
	 * 获得父节点菜单信息
	 * 
	 * @param menu
	 * @param menuList
	 * @param authedMenuList
	 */
	private void getParentMenu(Menu menu, List<Menu> menuList, List<Menu> authedMenuList) {
		if (!authedMenuList.contains(menu)) {
			authedMenuList.add(menu);
		}
		if (menu.getParentId() != null) {
			for (Menu item : menuList) {
				if (item.getId() == menu.getParentId()) {
					if (!authedMenuList.contains(item)) {
						authedMenuList.add(item);
						getParentMenu(item, menuList, authedMenuList);
					}
				}
			}
		}
	}

	/**
	 * 构造树型结构菜单
	 * 
	 * @param menusList
	 * @return
	 */
	public Menu CreateTreeNodes(List<Menu> menusList) {
		Menu rootNode = new Menu();

		for (Menu item : menusList) {
			if (item.getParentId() == null) {
				rootNode.getChildren().add(item);
				FillChildren(item, menusList, item.getId());
			}
		}
		return rootNode;
	}

	/**
	 * 填充子节点
	 * 
	 * @param node
	 * @param menusList
	 * @param nodeRootId
	 */
	private void FillChildren(Menu node, List<Menu> menusList, Long nodeRootId) {
		for (Menu item : menusList) {
			if (item.getParentId() == node.getId()) {
				node.getChildren().add(item);
				FillChildren(item, menusList, item.getId());
			}
		}
	}

	/**
	 * 根据角色ID查询授权菜单
	 * @param roleId
	 * @return
	 */
	 public List<RoleMenu> findByRoleId(Long roleId) {
		// 查询角色菜单列表
		List<RoleMenu> roleMenuList = roleMenuRepository.findByRoleId(roleId);
		return roleMenuList;
	}
}
