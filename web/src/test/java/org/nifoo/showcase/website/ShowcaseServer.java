package org.nifoo.showcase.website;

import org.eclipse.jetty.server.Server;
import org.nifoo.showcase.jetty.JettyFactory;

/**
 * 使用Jetty运行调试Web应用, 在Console快速重载应用.
 * 
 */
public class ShowcaseServer {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/showcase-website";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
		Profiles.setProfileAsSystemProperty(Profiles.UNIT_TEST);

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");

			server.start();
			System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("[HINT] Hit Enter to reload the application quickly");

			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
