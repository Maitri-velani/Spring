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

@WebServlet("/get")
public class GetServlet extends HttpServlet {

    private EmployeeDaoImpl employeeDao;
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter =resp.getWriter();

        String id =req.getParameter("id");
        Employee employee=new Employee();

        employee.setId(id);
        employeeDao =new EmployeeDaoImpl();
        Employee employeeResponse= employeeDao.get(id,getServletContext().getInitParameter("index"));
        printWriter.println(employeeResponse);

    }
}
