package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.EmployeeDaoImpl;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{

    //private RestHighLevelClient client = EsRestClient.getClient();
    private ObjectMapper objectMapper;
    private EmployeeDaoImpl employeeDao;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter =resp.getWriter();

        String city =req.getParameter("city");
        System.out.println(city);

      //  Employee employee=new Employee();
       // employee.setCity(city);
        employeeDao =new EmployeeDaoImpl();
        System.out.println(getServletContext().getInitParameter("index"));
        Employee employee=employeeDao.searchByCity(city,getServletContext().getInitParameter("index"));
    }


}
