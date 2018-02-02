package controller;

import dao.impl.EmployeeDaoImpl;
import model.Employee;
import org.elasticsearch.action.update.UpdateResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("text/html");
         PrintWriter printWriter=resp.getWriter();

         String id= req.getParameter("id");
         String name=req.getParameter("name");
         String city=req.getParameter("city");
         String email=req.getParameter("email");

         Employee employee = new Employee();
         employee.setId(id);
         employee.setName(name);
         employee.setCity(city);
         employee.setEmail(email);

         System.out.println(employee);

         EmployeeDaoImpl ed = new EmployeeDaoImpl();
         UpdateResponse updateResponse = ed.update(getServletContext().getInitParameter("index"),employee);

         printWriter.println(updateResponse.toString());
         printWriter.println(updateResponse.status());
    }
}
