package org.sj.oaprj.home.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.RespCodeData;
import org.sj.oaprj.domain.RespTreeNode;
import org.sj.oaprj.entity.CodeType;
import org.sj.oaprj.repository.CodeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeTypeServiceImpl {
	@Autowired
	private CodeTypeRepository codeDataRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public String save(CodeType entity) {
		CodeType ct = findOne(entity.getCodeType());
		// 新增
		if (Utils.isEmpty(entity.getId())) {
			if (Utils.isEmpty(ct.getId())) {
				CodeType codeType = codeDataRepository.save(entity);
				return Utils.isNull(codeType) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			} else {
				return Constants.CODE_TYPE_EXISTS;
			}
			// 更新
		} else {
			// 如果是同一对象,或者还未有当前要更改的codeType的值
			if (Utils.isEmpty(ct.getId()) || ct.getId().equals(entity.getId())) {
				CodeType codeType = codeDataRepository.save(entity);
				return Utils.isNull(codeType) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			} else {
				return Constants.CODE_TYPE_EXISTS;
			}
		}
	}

	public List<RespTreeNode> findAll() {
		Iterable<CodeType> codeTypeList = codeDataRepository.findAll();
		RespTreeNode root = new RespTreeNode();
		root.setId("");
		root.setText("全部");
		for (CodeType item : codeTypeList) {
			RespTreeNode children = new RespTreeNode();
			children.setId(Utils.toString(item.getCodeType()));
			children.setText(item.getTypeName());
			root.getChildren().add(children);
		}
		// 树型菜单结构列表
		List<RespTreeNode> nodeList = new ArrayList<RespTreeNode>();
		nodeList.add(root);
		return nodeList;
	}

	public void delete(Long id) {
		CodeType entity = codeDataRepository.findOne(id);
		entity.setDeleteFlag(Constants.DELETE_FLAG_1);
		codeDataRepository.save(entity);
	}

	public CodeType findOne(Integer codeType) {
		List<CodeType> codeTypeList = codeDataRepository.findByCodeType(codeType);
		if (codeTypeList != null && codeTypeList.size() > 0) {
			return codeTypeList.get(0);
		}
		return new CodeType();
	}

	public RespCodeData findTypeNameById(Long id) {
		CodeType codeType = codeDataRepository.findOne(id);
		RespCodeData respCode = new RespCodeData();
		respCode.setCodeType(codeType.getCodeType());
		respCode.setTypeName(codeType.getTypeName());
		return respCode;
	}
}
