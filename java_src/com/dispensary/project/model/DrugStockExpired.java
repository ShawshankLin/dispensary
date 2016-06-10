package com.dispensary.project.model;

public class DrugStockExpired {
	private String drugId;
	private String drugName;
	private int periodOfValidity;
	private String productionDate;
	private String nowDate;
	private String toDate;
	private int leftDay;
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getPeriodOfValidity() {
		return periodOfValidity;
	}
	public void setPeriodOfValidity(int periodOfValidity) {
		this.periodOfValidity = periodOfValidity;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getLeftDay() {
		return leftDay;
	}
	public void setLeftDay(int leftDay) {
		this.leftDay = leftDay;
	}
	
}
