package googlecloudpinger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/hello" })
public class HelloAppEngine extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HelloAppEngine.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// A list of URLS if we want to ping more than one.
		List<URL> urlList = new ArrayList<URL>();
		// An example URL, change this with your preferred URL
		/**
		 ** Openshift Online Version 2: Sunset
		 * urlList.add(new URL("http://mywildfly-emafazillah.rhcloud.com/"));
		 * urlList.add(new URL("http://mywildflyrestv2-emafazillah.rhcloud.com/clinicspanels/count/"));
		 * urlList.add(new URL("http://testcase2-emafazillahphp.rhcloud.com/"));
		 * urlList.add(new URL("http://api2-emafazillahdev.rhcloud.com/tbluser/count/"));
		 * urlList.add(new URL("http://apicliniclocator-ihssb.rhcloud.com/clinicspanels/count/"));
		 * urlList.add(new URL("http://apicliniclocatorv2-ihssb.rhcloud.com/clinicspanels/count/"));
		 */
		// Heroku
		urlList.add(new URL("https://malaysianpetrolprice.herokuapp.com/"));
		urlList.add(new URL("https://emkuliyyah.herokuapp.com/"));
		for (URL url : urlList) {
			try {
				// The GET request is generated here when openStream() is
				// executed.
				url.openStream();
				log.info("Ping: " + url + " ... done");
			} catch (IOException e) {
				/**
				 * This message is printed when the openStream() method fails. This could mean
				 * that the remote server answered with a 404/50x code for example, so the GET
				 * request may have been sent anyway.
				 */
				log.info("Error ping: " + url + "(GET request may be succeeded though)");
			}
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().print("Hello App Engine!\r\n");

	}
}