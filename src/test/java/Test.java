

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class Test {
    public static void path(HttpServletRequest request){
    	System.out.println(request.getPathInfo());
        System.out.println(request.getPathTranslated());
        System.out.println(request.getServletPath());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpServletRequest request = null;
		String ids="ff";
		String idArray[]=ids.split(",");
		for(int i=0;i<idArray.length;i++){
			System.out.println(idArray[i]);
		}
    	int[] idArray1=new int[idArray.length];
	}

}
