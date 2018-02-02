package controller;

import dao.impl.EmployeeDaoImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter=resp.getWriter();

        String name = req.getParameter("name");
        String city= req.getParameter("city");
        String email= req.getParameter("email");
        String id = req.getParameter("id");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setCity(city);
        employee.setEmail(email);

        EmployeeDaoImpl ed = new EmployeeDaoImpl();
        Boolean indexResponse = ed.insert(getServletContext().getInitParameter("index"),employee);

        if(indexResponse.equals(true)) {
            printWriter.println("Document created");
        }else{
            printWriter.println("Document not created");
        }
    }
}
