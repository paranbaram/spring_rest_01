package com.ice.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ice.rest.ErrorMessage;

@Controller
public class ICEController {
	/*pseudo persistance unit uses by the controller*/
	@Autowired
	private ICEService iceService;
	@Autowired
	private ErrorMessageXmlView errorMessageXmlView;
	
	@RequestMapping(value="internalapiinform/{ifId}",method = RequestMethod.GET)
	public @ResponseBody ICEmodel getICEItem(@PathVariable int ifId) {
		return iceService.getItem(ifId);
	}
	
	@RequestMapping(value="internalapiinformbyurl",method = RequestMethod.GET)
	public @ResponseBody List<ICEmodel> getICEItemsByUrl(
			@RequestParam(value="ifURL", required=false) String url,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="count", required=false, defaultValue="20") int count,
			@RequestParam(value="orderoption", required=false, defaultValue="DESC") String order) {
		return iceService.getItemByUrl(url,page,count,order);
	}
	
	@RequestMapping(value="internalapiinform",method = RequestMethod.GET)
	public @ResponseBody List<ICEmodel> getICEAllItems(
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="count", required=false, defaultValue="20") int count,
			@RequestParam(value="orderoption", required=false, defaultValue="DESC") String order) {
		return iceService.getAllItems(page,count,order);
	}

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleEmptyException (Exception ex) 
	{
		ErrorMessage errMsg = new ErrorMessage();
		errMsg.setCategory("system");
		errMsg.setCode("9402");
		errMsg.setId("400");
		errMsg.setMessage("요청 정보를 찾을 수 없습니다.");
		
		return new ModelAndView(errorMessageXmlView).addObject(ErrorMessage.class.getName(), errMsg);
	}
	
	
}
