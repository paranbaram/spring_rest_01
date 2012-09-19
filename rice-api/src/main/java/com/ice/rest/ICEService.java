package com.ice.rest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ice.dao.ICEDAO;
import com.ice.rest.ICEmodel;
import com.ice.rest.RequestInvalidException;

@Service
public class ICEService {
	@Autowired
	private ICEDAO iceDao;

	public ICEmodel getItem (int ifId) throws RequestInvalidException {
		HashMap iceInfo = new HashMap();
		iceInfo = iceDao.selectIce(ifId);
		if (null == iceInfo) {
			//TODO throw exception :NOT FOUND
			//InvalidRequestException (String msg, String code, String category) {
			throw new RequestInvalidException("요청 정보를 찾을 수 없습니다.", "9402", "system");
		}
		ICEmodel iceItem = new ICEmodel();
		iceItem = setItem(iceInfo);
		return iceItem;
	}
	
	public List<ICEmodel> getAllItems (int page, int count, String order) {
		HashMap param = new HashMap();
		page = page <= 1? 1 : page;
		param.put("offset", (page -1) * count);
		param.put("count", count);
		param.put("order", order);
		
		List<HashMap> iceInforms = new ArrayList<HashMap>();
		iceInforms = iceDao.selectAllIce(param);
		if (null == iceInforms) {
			//TODO : throw exception : NOT FOUND
		}
		List<ICEmodel> iceItems =  new ArrayList<ICEmodel> ();
		for (int i = 0; i < iceInforms.size(); i++) {
			HashMap iceInfo = new HashMap(iceInforms.get(i));
			ICEmodel iceItem = new ICEmodel();
			iceItem = setItem(iceInfo);
			iceItems.add(iceItem);
		}
		return iceItems;
	}
	
	private ICEmodel setItem (HashMap data) {
		ICEmodel iceItem = new ICEmodel();
		iceItem.setIfCategory(data.get("category_path") + ">" + data.get("category_name"));
		iceItem.setIfDesc((String)data.get("memo1"));
		iceItem.setIfID(String.valueOf(data.get("if_id")));
		iceItem.setIfManager((String)data.get("user_id"));
		iceItem.setIfName((String)data.get("if_name"));
		iceItem.setIfType((String)data.get("if_type"));
		iceItem.setIfURL((String)data.get("if_url"));
		iceItem.setInputParams(parseStringJsonDB((String)data.get("spec_in")));
		
		return iceItem;
	}
	
	private List<HashMap> parseStringJsonDB (String json) {
		
		List<List<String>> paramStringList = new ArrayList<List<String>>();
		List<HashMap> paramMapList = new ArrayList<HashMap>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			paramStringList = mapper.readValue(json, new TypeReference<List<List<String>>>() { });
			//	paramHashMaps.get(0) = 1th row : title row - skip
			//	params[0]:[Parameter, 이름, 타입, 예시, 기본값, 상세설명]
			//	params[1]:[loginId, 로그인아이디, string, "12345678", , ]	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 1; i < paramStringList.size(); i++ ) {
			HashMap<String,String> param = new HashMap<String,String>();
			param.put("ParamName", paramStringList.get(i).get(0));
			param.put("ParamType", paramStringList.get(i).get(2));
			param.put("ParamDesc", paramStringList.get(i).get(1));
			
			paramMapList.add(param);
		}	
		
		return paramMapList;
	}

	public List<ICEmodel> getItemByUrl(String url, int page, int count, String order) {
		HashMap param = new HashMap();
		page = page <= 1? 1 : page;
		param.put("offset", (page -1) * count);
		param.put("count", count);
		param.put("order", order);
		param.put("url", url);
		
		List<HashMap> iceInforms = new ArrayList<HashMap>();
		iceInforms = iceDao.selectIceByUrl(param);
		if (null == iceInforms) {
			//TODO : throw exception : NOT FOUND
		}
		
		List<ICEmodel> iceItems =  new ArrayList<ICEmodel> ();
		for (int i = 0; i < iceInforms.size(); i++) {
			HashMap iceInfo = new HashMap(iceInforms.get(i));
			ICEmodel iceItem = new ICEmodel();
			iceItem = setItem(iceInfo);
			iceItems.add(iceItem);
		}
		return iceItems;
	}
}
