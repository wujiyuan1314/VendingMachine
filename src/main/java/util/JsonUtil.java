package util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Json处理相关类
 * @author ylsoft
 *
 */
public class JsonUtil {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	static {
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
   public static Object getObject4JsonString(String jsonString,Class<?> pojoCalss){
       Object pojo;
       JSONObject jsonObject = JSONObject.fromObject( jsonString );  
       pojo = JSONObject.toBean(jsonObject,pojoCalss);
       return pojo;
   }

   public static Map<String, Object> getMap4Json(String jsonString){
       JSONObject jsonObject = JSONObject.fromObject( jsonString );  
       Iterator<?>  keyIter = jsonObject.keys();
       String key;
       Object value;
       Map<String, Object> valueMap = new HashMap<String, Object>();

       while( keyIter.hasNext())
       {
           key = (String)keyIter.next();
           value = jsonObject.get(key);
           valueMap.put(key, value);
       }
       
       return valueMap;
   }
   
   public static Object[] getObjectArray4Json(String jsonString){
       JSONArray jsonArray = JSONArray.fromObject(jsonString);
       return jsonArray.toArray();
       
   }
   

   public static List<Object> getList4Json(String jsonString, Class<?> pojoClass){
       
       JSONArray jsonArray = JSONArray.fromObject(jsonString);
       JSONObject jsonObject;
       Object pojoValue;
       
       List<Object> list = new ArrayList<Object>();
       for ( int i = 0 ; i<jsonArray.size(); i++){
           
           jsonObject = jsonArray.getJSONObject(i);
           pojoValue = JSONObject.toBean(jsonObject,pojoClass);
           list.add(pojoValue);
           
       }
       return list;

   }

   public static String[] getStringArray4Json(String jsonString){
       
       JSONArray jsonArray = JSONArray.fromObject(jsonString);
       String[] stringArray = new String[jsonArray.size()];
       for( int i = 0 ; i<jsonArray.size() ; i++ ){
           stringArray[i] = jsonArray.getString(i);
           
       }
       
       return stringArray;
   }
   public static String getJsonString4JavaPOJO(Object javaObj){
       
       JSONObject json;
       json = JSONObject.fromObject(javaObj);
       return json.toString();
       
   }
   
   public static void main(String[] args) {

   }
   
   public static <T> T deserialize(String content, TypeReference<T> typeReference) {
		try {
			T obj = OBJECT_MAPPER.readValue(content, typeReference);
			return obj;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String serialize(Object obj) {
		try {
			String json = OBJECT_MAPPER.writeValueAsString(obj);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}

