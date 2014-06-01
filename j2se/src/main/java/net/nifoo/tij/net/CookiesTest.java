package net.nifoo.tij.net;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class CookiesTest {

	public static void main(String[] args) throws Exception {
		CookieManager cm = new CookieManager();
		CookieHandler.setDefault(cm);

		CookieStore cs = cm.getCookieStore();
		//		URI uri = new URI("http://www.baidu.com");
		//		for (HttpCookie cookie : cs.get(uri)){
		//			System.out.printf("Path: %s", cookie.getPath());
		//			System.out.printf("%s: %s", cookie.getName(), cookie.getValue());
		//		}

		URL url = new URL("http://gmail.com");
		System.out.println(url.getContent());
		System.out.println();

		// 使用 Cookie 的时候：        
//		List<URI> uris = cs.getURIs(); // 得到所有的 URI 
//		for (URI uri : uris) {
//			// 筛选需要的 URI, 得到属于这个 URI 的所有 Cookie
//			List<HttpCookie> cookies = cs.get(uri);
//			for (HttpCookie cookie : cookies) {
//				System.out.printf("Path: %s \n", cookie.getPath());
//				System.out.printf("%s: %s \n\n", cookie.getName(), cookie.getValue());
//			}
//		}

		List<HttpCookie> cookies = cs.getCookies();
		for (HttpCookie cookie : cookies) {
			System.out.printf("Path: %s \n", cookie.getPath());
			System.out.printf("%s: %s \n\n", cookie.getName(), cookie.getValue());
		}

	}

}
