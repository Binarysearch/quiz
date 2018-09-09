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

	public int getInt(String paramName) {
        String paramValue = getString(paramName);
        try{
            return Integer.parseInt(paramValue);
        } catch(NumberFormatException e){
            throw new ApiException(400, "El formato del parametro '"+paramValue+"' no es valido, debe ser un numero entero.");
        }
	}

}