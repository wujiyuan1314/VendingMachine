

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
		String ids="";
		String idArray[]=ids.split("3");
		System.out.println(idArray.length);
		//.out.println(idArray[2]);
		for(int i=0;i<idArray.length;i++){
			System.out.println(idArray[i]+"ff");
		}
    	int[] idArray1=new int[idArray.length];
	}

}
