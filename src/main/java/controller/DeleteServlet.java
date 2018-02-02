package controller;

import dao.impl.EmployeeDaoImpl;
import model.Employee;
import org.elasticsearch.action.delete.DeleteResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter=resp.getWriter();

        String id= req.getParameter("id");

        Employee employee = new Employee();
        employee.setId(id);

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        DeleteResponse deleteResponse= employeeDao.delete(getServletContext().getInitParameter("index"),employee);
        printWriter.println(deleteResponse.status());
    }
}
