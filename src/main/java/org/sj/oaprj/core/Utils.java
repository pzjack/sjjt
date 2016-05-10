package org.sj.oaprj.core;

import org.springframework.data.domain.Pageable;

public class Utils {
	/*
	 * 判断字符串是否为空字符串
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		return value.toString().trim().length() == 0;
	}

	/**
	 * Object转为String
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString().trim();
	}

	public static Long toLong(Object value) {
		if (value == null) {
			return (long) 0;
		}
		return Long.parseLong(value.toString());
	}

	public static Integer toInteger(Object value) {
		if (value == null) {
			return 0;
		}
		return Integer.parseInt(value.toString());
	}

	/**
	 * 
	 * getStartIndex(计算每页的起始下标)
	 * 
	 * @param Pageable
	 *            页码信息 @param total 数据总笔数 @return int 当前页起始下标 @exception
	 */
	public static int getStartIndex(Pageable pageable, long total) {
		return getStartIndex(pageable.getPageNumber(), pageable.getPageSize(), total);
	}

	/**
	 * 
	 * getStartIndex(计算每页的起始下标)
	 * 
	 * @param pageIndex
	 *            当前页码 @param pageSize 每页 笔数 @param total 数据总笔数 @return int
	 *            当前页起始下标 @exception
	 */
	public static int getStartIndex(int pageIndex, int pageSize, long total) {
		if (pageIndex == 0) {
			return 0;
		}
		// 当前页码开始下标大于记录总数时，重置当前页码，主要用于删除时，数据笔数不足时的页码重置
		else if ((pageIndex - 1) * pageSize + 1 > total) {
			pageIndex = (int) Math.ceil(total / pageSize);
			pageIndex = pageIndex == 0 ? 1 : pageIndex;
		}
		// 计算每页起始下标
		return (pageIndex - 1) * pageSize;
	}
}
