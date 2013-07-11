package servlety;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Zapisnik extends HttpServlet {

    List<String> zapisky = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //nastavení kódování
        request.setCharacterEncoding("UTF-8");

        //část sloužící pro zápis nové poznámky
        String zapisek = request.getParameter("zapisek");
        if (zapisek != null && !zapisek.equals("")) {
            zapisky.add(zapisek);
        }

        //část sloužící pro vygenerování výsledné stránky    
        request.setAttribute("zapisky", zapisky);
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/index.jsp");
        disp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {

        return "Servlet k ničemu";

    }// </editor-fold>
}