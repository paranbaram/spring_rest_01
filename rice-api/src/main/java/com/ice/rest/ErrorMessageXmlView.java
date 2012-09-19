package com.ice.rest;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

public class ErrorMessageXmlView extends AbstractView implements InitializingBean {
    
    private String modelKey;
    private String characterEncoding;
    
    private Marshaller marshaller;
    
    public String getModelKey() {
        return modelKey;
    }
    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }
    
    public String getCharacterEncoding() {
        return characterEncoding;
    }
    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if(!StringUtils.hasText(modelKey))
            this.modelKey = ErrorMessage.class.getName();
        if(!StringUtils.hasText(characterEncoding))
            this.characterEncoding = "UTF-8";
        
        JAXBContext jaxbContext = JAXBContext.newInstance(ErrorMessage.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_ENCODING, characterEncoding);
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        response.setCharacterEncoding(characterEncoding);
        response.setHeader("Cache-Control", "no-cache");
        
        marshaller.marshal(findErrorMessage(model), new StreamResult(response.getOutputStream()));
    }
    
    protected ErrorMessage findErrorMessage(Map<String, Object> model) throws ServletException {
        Object errorMessage = model.get(this.modelKey);
        if(errorMessage == null) {
            throw new ServletException("Model contains no object with key [" + modelKey + "]");
        }
        if(!ErrorMessage.class.isAssignableFrom(errorMessage.getClass())) {
            throw new ServletException("Model object [" + errorMessage + "] retrieved via key [" + 
                        modelKey + "] is not ErrorMessage Type");
        }
        return (ErrorMessage) errorMessage;
    }

}