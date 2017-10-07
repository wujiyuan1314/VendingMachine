package vend.service.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.EncodeUtils;
import base.util.HttpClientUtil;
import net.sf.json.JSONObject;
import vend.dao.CodeLibraryMapper;
import vend.dao.VendParaMapper;
import vend.entity.CodeLibrary;
import vend.entity.VendPara;
import vend.service.GetWeiXinUserInfoService;
@Service
public class GetWeiXinUserInfoServiceImpl implements GetWeiXinUserInfoService{
	private static Logger logger = Logger.getLogger(GetWeiXinUserInfoServiceImpl.class.getName());
	@Autowired
    VendParaMapper vendParaMapper;
	@Autowired
	CodeLibraryMapper codeLibraryMapper;
	/**
	 * 获取微信用户的code
	 * @return
	 */
	public String getCode(String wechatpubNo){
		String retMsg="";
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		String redirect_uri=EncodeUtils.urlEncode("http://zdypx.benbenniaokeji.com/mobile/getcode");
		if(codeLibrary!=null){
			String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+codeLibrary.getItemname()+""
					+ "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
			retMsg=HttpClientUtil.httpGetRequest(url);
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
			}**/
		}
		return retMsg;
	}
	/**
	 * 通过code换取网页授权access_token和openid
	 * @return
	 */
	public Map<String,String> getAccessToken(String wechatpubNo,String code){
		logger.info("-----------------------开始获取微信用户access_token和openid-----------------------");
		Map<String,String> map=new HashMap<String,String>();
		logger.info("---------getAccessToken------map是否为空----------"+map.isEmpty());
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		if(codeLibrary!=null){
			String urlStr="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+codeLibrary.getItemname()+""
					+ "&secret="+codeLibrary.getExtend1()+"&code="+code+"&grant_type=authorization_code";
			
			//处理url地址,避免Illegal character in scheme name at index 0错误
			URL url;
			URI uri = null;
			try {
				url = new URL(urlStr);
				uri = new URI(url.getProtocol(),url.getHost(),url.getPath(),url.getQuery(),null);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			String retMsg=HttpClientUtil.httpGetRequest(uri);
			logger.info("---------------公众号"+codeLibrary.getItemno()+"access_token获取用户信息的retMsg----------"+retMsg);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);
				logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的access_token返回信息----------"+retJson);
				if(retJson.has("access_token")){
					String access_token = retJson.getString("access_token");
					String openid = retJson.getString("openid");
					map.put("access_token", access_token);
					map.put("openid", openid);
					logger.info("---------getAccessToken------map是否为空----------"+map.isEmpty());
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
		logger.info("---------getAccessToken------map是否为空----------"+map.isEmpty());
		logger.info("-----------------------结束获取微信用户access_token和openid-----------------------");
		return map;
	}
	/**
	 * 通过access_token,openid获取微信用户信息
	 * @return
	 */
	public Map<String,String> getWxUserInfo(String wechatpubNo,String access_token,String openid){
		logger.info("-----------------------开始获取微信用户信息-----------------------");
		logger.info("-----------------------开始获取微信用户信息wechatpubNo的值-----------------------"+wechatpubNo);
		logger.info("-----------------------开始获取微信用户信息access_token的值-----------------------"+access_token);
		logger.info("-----------------------开始获取微信用户信息openid的值-----------------------"+openid);
		Map<String,String> map=new HashMap<String,String>();
		logger.info("---------getWxUserInfo------map是否为空----------"+map.isEmpty());
		CodeLibrary codeLibrary=codeLibraryMapper.selectByItemno("WECHATPUBNO", wechatpubNo);
		if(codeLibrary==null){
			logger.info("-----------------------开始获取微信用户信息codeLibrary为null-----------------------");
		}
		if(codeLibrary!=null){
			String urlStr=" https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token+"&openid="+openid+"&lang=en";
			logger.info("-----------------------开始获取微信用户信息url是-----------------------"+urlStr);
			
			//处理url地址,避免Illegal character in scheme name at index 0错误
			URL url;
			URI uri = null;
			try {
				url = new URL(urlStr);
				uri = new URI(url.getProtocol(),url.getHost(),url.getPath(),url.getQuery(),null);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			String retMsg=HttpClientUtil.httpGetRequest(uri);
			logger.info("---------------公众号"+codeLibrary.getItemno()+"getWxUserInfo获取用户信息的retMsg----------"+retMsg);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);
				logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的getWxUserInfo返回信息----------"+retJson);
				if(retJson.has("openid")){
					String nickname = retJson.getString("nickname");
					String city = retJson.getString("city");
					map.put("nickname", nickname);
					map.put("city", city);
					logger.info("---------getWxUserInfo------map是否为空----------"+map.isEmpty());
					logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的nickname----------"+nickname);
					logger.info("---------------公众号"+codeLibrary.getItemno()+"获取用户信息的city----------"+city);
				}else{
					String errcode = retJson.getString("errcode");
					String errmsg = retJson.getString("errmsg");
					logger.info("公众号"+codeLibrary.getItemno()+"的获取用户信息的access_token:-----错误码："+errcode+"-----错误信息:"+errmsg);
				}
			}
		}
		logger.info("---------getWxUserInfo------map是否为空----------"+map.isEmpty());
		logger.info("-----------------------结束获取微信用户信息-----------------------");
		return map;
	}
}
