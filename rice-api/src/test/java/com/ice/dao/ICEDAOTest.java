package com.ice.dao;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration({"classpath:applicationContext.xml"})
public class ICEDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ICEDAO icedao;
	@Test
	public void testSelectIce() {
		HashMap result = icedao.selectIce(325);
		ObjectMapper mapper = new ObjectMapper();
		assertEquals("api", result.get("if_type"));
		
		String json = (String)result.get("spec_in");
	
		System.out.println(json);
		List<List<String>> params = null;
		
		try {
			params = mapper.readValue(json, new TypeReference<List<List<String>>>() { });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("params=" + params.size());
		for(int i = 0; i < params.size(); i++) {
			System.out.println("params["+ i + "]:" + params.get(i));
		}
	
	}
	/*
	@Test
	public void testSelectCategory() {
		fail("Not yet implemented");
	}
	*/

}
