package com.ice.rest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class ICEmodel {

	private String IfID;
	private String IfURL;
	private String IfName;
	private String IfType;
	private String IfCategory;
	private String IfDesc;
	private String IfManager;
	
	private List<HashMap> InputParams;
	private List<HashMap> OutputParams;

	public String getIfID() {
		return IfID;
	}
	public void setIfID(String ifID) {
		IfID = ifID;
	}
	public String getIfURL() {
		return IfURL;
	}
	public void setIfURL(String ifURL) {
		IfURL = ifURL;
	}
	public String getIfName() {
		return IfName;
	}
	public void setIfName(String ifName) {
		IfName = ifName;
	}
	public String getIfType() {
		return IfType;
	}
	public void setIfType(String ifType) {
		IfType = ifType;
	}
	public String getIfCategory() {
		return IfCategory;
	}
	public void setIfCategory(String ifCategory) {
		IfCategory = ifCategory;
	}
	public String getIfDesc() {
		return IfDesc;
	}
	public void setIfDesc(String ifDesc) {
		IfDesc = ifDesc;
	}
	public String getIfManager() {
		return IfManager;
	}
	public void setIfManager(String ifManager) {
		IfManager = ifManager;
	}
	public List<HashMap> getInputParams() {
		return InputParams;
	}
	public void setInputParams(List<HashMap> inputParams) {
		InputParams = inputParams;
	}
	public List<HashMap> getOutputParams() {
		return OutputParams;
	}
	public void setOutputParams(List<HashMap> outputParams) {
		OutputParams = outputParams;
	}
	
}
