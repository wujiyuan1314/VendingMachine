package base.init;

import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;

import base.util.SysPara;

public class InitConfigServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private Logger log = Logger.getLogger(InitConfigServlet.class);
  Thread server = null;
  
  public void init(ServletConfig paramServletConfig)
    throws ServletException
  {
    SysPara.REAL_PATH = paramServletConfig.getServletContext().getRealPath("");
    super.init(paramServletConfig);
    initPara();
  }
  
  public void initPara()
  {
    try
    {
      InputStream localInputStream = InitConfigServlet.class.getClassLoader().getResourceAsStream("/global.properties");
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      SysPara.midPublishUrl = localProperties.getProperty("midPublishUrl");
      SysPara.midQueryUrl =  localProperties.getProperty("midQueryUrl");
     
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.log.error("InitConfigServlet-->initPara() 获取配置文件或读取数据库固定参数错误，请查证！");
    }
  }
  
  public void destroy()
  {
    super.destroy();
  }
}
