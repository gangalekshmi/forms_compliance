package com.cognizant.model;

public class Employee {

	public String empId;
	public String empName;
	public boolean formA;
	public boolean formB;
	public String mngrName;
	public String prjName;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public boolean isFormA() {
		return formA;
	}

	public void setFormA(boolean formA) {
		this.formA = formA;
	}

	public boolean isFormB() {
		return formB;
	}

	public void setFormB(boolean formB) {
		this.formB = formB;
	}

	public String getMngrName() {
		return mngrName;
	}

	public void setMngrName(String mngrName) {
		this.mngrName = mngrName;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", FormA=" + formA + ", FormB=" + formB
				+ ", mngrName=" + mngrName + ", prjName=" + prjName + "]";
	}

}
