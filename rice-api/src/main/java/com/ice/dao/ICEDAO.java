package com.ice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class ICEDAO {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
	}
	
	public HashMap selectIce(int id) {
		HashMap param = new HashMap();
		param.put("id", id);
		return (HashMap) sqlMapClientTemplate.queryForObject("ice.getIceInfo", param);
	}
	
	public List selectAllIce(HashMap param) {
		return sqlMapClientTemplate.queryForList("ice.getIceInfo",param);
	}
	
	public List selectIceByUrl(HashMap param) {
		return sqlMapClientTemplate.queryForList("ice.getIceInfoByUrl", param);
	}
}
