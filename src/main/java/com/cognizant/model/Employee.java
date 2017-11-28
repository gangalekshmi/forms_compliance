package com.cognizant.model;

import java.util.List;

public class Employee {

	public String empid;
	public String empname;		
	public boolean ndcFormCompliant;
	public boolean ciaFormCompliant;
	public String MngrName;
	
	public List<Project> prj_assignments;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}


	public String getMngrName() {
		return MngrName;
	}

	public void setMngrName(String mngrName) {
		MngrName = mngrName;
	}

	public List<Project> getPrj_assignments() {
		return prj_assignments;
	}

	public void setPrj_assignments(List<Project> prj_assignments) {
		this.prj_assignments = prj_assignments;
	}

	public boolean isNdcFormCompliant() {
		return ndcFormCompliant;
	}

	public void setNdcFormCompliant(boolean ndcFormCompliant) {
		this.ndcFormCompliant = ndcFormCompliant;
	}

	public boolean isCiaFormCompliant() {
		return ciaFormCompliant;
	}

	public void setCiaFormCompliant(boolean ciaFormCompliant) {
		this.ciaFormCompliant = ciaFormCompliant;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", ndcFormCompliant=" + ndcFormCompliant
				+ ", ciaFormCompliant=" + ciaFormCompliant + ", MngrName=" + MngrName +"]";
	}

	
	
}
