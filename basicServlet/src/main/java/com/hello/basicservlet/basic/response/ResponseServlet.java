package com.hello.basicservlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-html")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //[status line]
        resp.setStatus(HttpServletResponse.SC_OK);
        // response-headers
        resp.setHeader("Content-Type","text/html");
        resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("my-header","hi");

        PrintWriter writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<div>H~I~?</div>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
