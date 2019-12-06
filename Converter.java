
import java.text.DecimalFormat;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Converter
 */
@WebServlet("/Converter")
public class Converter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Converter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        
		float rate = 1.0f;
		//previous session exists
		if (session != null) {
			rate = (float)session.getAttribute("rate");
        }
        else { //create a new session
        	session = request.getSession(true);
        	session.setAttribute("rate", rate);
        }
		
		PrintWriter out = response.getWriter();
        out.println (
                  "<!DOCTYPE html>\r\n" + 
                  "<html>\r\n" + 
                  "<head>\r\n" + 
                  "	<meta charset=\"ISO-8859-1\">\r\n" + 
                  "	<title>Converter</title>\r\n" + 
                  "	<style>\r\n" + 
                  "		table, th, td {\r\n" + 
                  "			border: 1px solid black;\r\n" + 
                  "		}\r\n" + 
                  "		\r\n" + 
                  "		table th, table td {\r\n" + 
                  "			padding-left: 5px;\r\n" + 
                  "			padding-right: 5px;\r\n" + 
                  "		}\r\n" + 
                  "	</style>\r\n" + 
                  "</head>\r\n" + 
                  "<body>\r\n"
                  + "<form method=\"POST\" action=\"“/converter/servlet/Converter\">");
        out.println("Amount: <input type=\"text\" name=\"amount\"/><br/>");
        out.println("Rate: <input type=\"text\" name=\"rate\" value=\"" + rate + "\"/><br/><br/>");
        out.println("<button type=\"submit\">Convert!</button>");
        out.println ("</form></body>");
		out.println ("</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		float rate;
		
		if (session != null) {
			if(request.getParameter("rate") != "") {
				rate = Float.parseFloat(request.getParameter("rate"));
			} else {
				rate = (float)session.getAttribute("rate");
			}
			
			session.setAttribute("rate", rate);
			float amount = Float.parseFloat(request.getParameter("amount"));
			
			PrintWriter out = response.getWriter();
	        out.println (
	                  "<!DOCTYPE html>\r\n" + 
	                  "<html>\r\n" + 
	                  "<head>\r\n" + 
	                  "	<meta charset=\"ISO-8859-1\">\r\n" + 
	                  "	<title>Converter</title>\r\n" + 
	                  "	<style>\r\n" + 
	                  "		table, th, td {\r\n" + 
	                  "			border: 1px solid black;\r\n" + 
	                  "		}\r\n" + 
	                  "		\r\n" + 
	                  "		table th, table td {\r\n" + 
	                  "			padding-left: 5px;\r\n" + 
	                  "			padding-right: 5px;\r\n" + 
	                  "		}\r\n" + 
	                  "	</style>\r\n" + 
	                  "</head>\r\n" + 
	                  "<body>\r\n"
	                  + "Conversion result: ");
	        out.print(new DecimalFormat("#.00").format(rate * amount));
	        out.println ("</body>");
			out.println ("</html>");
        }
	}

}
