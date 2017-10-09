

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.util.StringUtils;

import base.util.EncodeUtils;
import base.util.Function;
import base.util.HttpClientUtil;
import base.weixinpay.common.StreamUtil;

public class Test1 {
    public static void path(HttpServletRequest request){
    	System.out.println(request.getPathInfo());
        System.out.println(request.getPathTranslated());
        System.out.println(request.getServletPath());
    }
	public static void main(String[] args) throws UnsupportedEncodingException {
		
	}

}
