package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/detail")
public class MvcMain extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
    {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html");

        String button=request.getParameter("btn");


        if (button.equals("Insert"))
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("insert");
            requestDispatcher.forward(request,response);
        }
        else if (button.equals("Update"))
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("update");
            requestDispatcher.forward(request,response);
        }
        else if (button.equals("Delete"))
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete");
            requestDispatcher.forward(request,response);
        }
        else if(button.equals("Get"))
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("get");
            requestDispatcher.forward(request,response);
        }
        else
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("search");
            requestDispatcher.forward(request,response);
        }
    }
}
