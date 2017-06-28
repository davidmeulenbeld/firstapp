package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int inp_1 = Integer.parseInt(req.getParameter("inp_1"));
		int inp_2 = Integer.parseInt(req.getParameter("inp_2"));
		
		Calc cl = new Calc(inp_1, inp_2);
		int som = cl.Bereken(req.getParameter("methode"));
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <head>");
		out.println(" <meta charset=\"UTF-8\">");
		out.println(" <title>Dynamic Example</title>");
		out.println(" </head>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>Uitkomst van som " +som+ "!</h2>");
		out.println(" </body>");
		out.println("</html>");

	}
}
