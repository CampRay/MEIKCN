/**   
 * @Title: RightsDataTableParamter.java 
 * @Package com.bps.model 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 1, 2014 11:59:56 AM
 * @version V1.0   
 */ 
package com.nuvomed.model;

/** 
 * <p>Types Description</p>
 * @ClassName: RightsDataTableParamter 
 * @author Phills Li 
 * 
 */
public class DataTableParamter {
	
    // DataTable请求服务器端次数
	public String sEcho;
	
	// 过滤条件
	public String sSearch;
	
	// 每页显示的数量
	public int iDisplayLength;
	
	// 分页时每页跨度数量
	public int iDisplayStart;
	
	// 列数
	public int iColumns;
	
	// 排序列的数量
	public int iSortingCols;
	
	// 逗号分割所有的列
	public String sColumns;
	
	public String delIds;
    
	public String order_id;
	
	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
}
