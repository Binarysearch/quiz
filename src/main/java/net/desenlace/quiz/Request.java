package net.desenlace.quiz;

import javax.servlet.http.HttpServletRequest;

public class Request {
    
    private HttpServletRequest request;

    public Request(HttpServletRequest request){
        this.request = request;
    }

    public String getString(String paramName){
        String result = this.request.getParameter(paramName);
        if(result == null){
            throw new ApiException(400, "El parametro '"+paramName+"' es obligatorio");
        }
        return result;
    }

}