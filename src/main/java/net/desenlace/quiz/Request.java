package net.desenlace.quiz;

import javax.servlet.http.HttpServletRequest;

public class Request {
    
    private HttpServletRequest request;

    public Request(HttpServletRequest request){
        this.request = request;
    }

    /**
     * Obtiene el valor de un parametro de la peticion.
     * @param paramName El nombre del parametro
     * @return El valor del parametro
     * @throws una ApiException si en la peticion no existe el parametro indicado.
     */
    public String getString(String paramName){
        String result = this.request.getParameter(paramName);
        if(result == null){
            throw new ApiException(400, "El parametro '"+paramName+"' es obligatorio");
        }
        return result;
    }

    /**
     * Obtiene el valor de un parametro de la peticion.
     * @param paramName El nombre del parametro
     * @param defaultValue El valor por defecto
     * @return El valor del parametro si existe. Si el parametro no existe la fucnion devuelve el 
     * valor indicado en defaultValue
     */
    public String getString(String paramName, String defaultValue){
        String result = this.request.getParameter(paramName);
        if(result != null){
            return result;
        }
        return defaultValue;
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