

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import base.util.Function;

public class Test {
    public static void path(HttpServletRequest request){
    	System.out.println(request.getPathInfo());
        System.out.println(request.getPathTranslated());
        System.out.println(request.getServletPath());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*HttpServletRequest request = null;
		String ids="";
		String idArray[]=ids.split("3");
		System.out.println(idArray.length);
		//.out.println(idArray[2]);
		for(int i=0;i<idArray.length;i++){
			System.out.println(idArray[i]+"ff");
		}
    	int[] idArray1=new int[idArray.length];
    	
    	//String chars = "abcdefghijklmnopqrstuvwxyz";
    	//System.out.println(chars.charAt((int)(Math.random() * 26)));*/
		String array[]=new String[3];
		System.out.println(array.length);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+"ff");
		}
		String m=null;
		array=m.split(":");
		System.out.println(array.length);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+"gg");
		}
		
    	//System.out.println(m.indexOf("ghjgj,"));
		
	}

}
