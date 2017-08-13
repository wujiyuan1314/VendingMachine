package exception;
/**
 * @author wujiyuan
 * @Date 2017/6/28 16:33
 * @Comments 处理导入导出excel的异常类
 */
public class ExcelException extends Exception {
	
	public ExcelException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public ExcelException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public ExcelException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public ExcelException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
}
