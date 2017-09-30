package base.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import vend.dao.CodeLibraryMapper;
import vend.dao.VendParaMapper;
import vend.entity.CodeLibrary;
import vend.entity.VendPara;

/**
 * 获取微信用户的信息
 * @author wujiyuan
 *
 */
@Component
public class GetWeiXinUserInfoUtil {
	private static Logger logger = Logger.getLogger(GetWeiXinUserInfoUtil.class.getName());
	@Autowired
	static VendParaMapper vendParaMapper;
	@Autowired
	static CodeLibraryMapper codeLibraryMapper;
	/**
	 * 获取微信用户的code
	 * @return
	 */
	public static String getCode(String wechatpubNo){
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		String redirect_uri=EncodeUtils.urlEncode("http://zdypx.benbenniaokeji.com/mobile/getcode");
		if(codeLibrary!=null){
			String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+codeLibrary.getItemname()+""
					+ "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			String retMsg=HttpClientUtil.httpGetRequest(url);
			logger.info("---------------公众号"+codeLibrary.getItemno()+"code获取用户信息的retMsg----------"+retMsg);
			/**if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);
				logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的code返回信息----------"+retJson);
				if(retJson.has("access_token")){
					String access_token = retJson.getString("access_token");
					VendPara vendPara=vendParaMapper.selectByParaCode(codeLibrary.getItemno()+"access_token");
					if(vendPara!=null){
						vendPara.setParaContent(access_token);
						vendParaMapper.updateByPrimaryKey(vendPara);
					}
					logger.info("---------------公众号"+codeLibrary.getItemno()+"的获取用户信息的code----------"+access_token);
				}else{
					String errcode = retJson.getString("errcode");
					String errmsg = retJson.getString("errmsg");
					logger.info("公众号"+codeLibrary.getItemno()+"的获取用户信息的code:-----错误码："+errcode+"-----错误信息:"+errmsg);
				}
			}*/
		}
		return null;
	}
	/**
	 * 通过code换取网页授权access_token和openid
	 * @return
	 */
	public static Map<String,String> getAccessToken(String wechatpubNo,String code){
		Map<String,String> map=new HashMap<String,String>();
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		if(codeLibrary!=null){
			String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+codeLibrary.getItemname()+""
					+ "&secret="+codeLibrary.getExtend1()+"&code="+code+"&grant_type=authorization_code";
			String retMsg=HttpClientUtil.httpGetRequest(url);
			logger.info("---------------公众号"+codeLibrary.getItemno()+"access_token获取用户信息的retMsg----------"+retMsg);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);
				logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的access_token返回信息----------"+retJson);
				if(retJson.has("access_token")){
					String access_token = retJson.getString("access_token");
					String openid = retJson.getString("openid");
					map.put("access_token", access_token);
					map.put("openid", openid);
					VendPara vendPara=vendParaMapper.selectByParaCode(codeLibrary.getItemno()+"access_token");
					if(vendPara!=null){
						vendPara.setParaContent(access_token);
						vendParaMapper.updateByPrimaryKey(vendPara);
					}
					logger.info("---------------公众号"+codeLibrary.getItemno()+"的获取用户信息的access_token----------"+access_token);
				}else{
					String errcode = retJson.getString("errcode");
					String errmsg = retJson.getString("errmsg");
					logger.info("公众号"+codeLibrary.getItemno()+"的获取用户信息的access_token:-----错误码："+errcode+"-----错误信息:"+errmsg);
				}
			}
		}
		return map;
	}
	/**
	 * 通过access_token,openid获取微信用户信息
	 * @return
	 */
	public static Map<String,String> getWxUserInfo(String wechatpubNo,String access_token,String openid){
		Map<String,String> map=new HashMap<String,String>();
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		if(codeLibrary!=null){
			String url=" https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
			String retMsg=HttpClientUtil.httpGetRequest(url);
			logger.info("---------------公众号"+codeLibrary.getItemno()+"getWxUserInfo获取用户信息的retMsg----------"+retMsg);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);
				logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的getWxUserInfo返回信息----------"+retJson);
				if(retJson.has("openid")){
					String nickname = retJson.getString("nickname");
					String city = retJson.getString("city");
					map.put("nickname", nickname);
					map.put("city", city);
					logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的nickname----------"+nickname);
					logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的city----------"+city);
				}else{
					String errcode = retJson.getString("errcode");
					String errmsg = retJson.getString("errmsg");
					logger.info("公众号"+codeLibrary.getItemno()+"的获取用户信息的access_token:-----错误码："+errcode+"-----错误信息:"+errmsg);
				}
			}
		}
		return map;
	}
}
