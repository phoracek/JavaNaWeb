package servlety;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modely.Zapisek;

@WebServlet(name = "Controller", urlPatterns = {"/zapisky", "/upravit", "/pridat", "/smazat", "/ulozitupravy"})
public class Controller extends HttpServlet {

    public List<Zapisek> zapisky = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adresa = request.getServletPath();

        if (adresa.equals("/zapisky")) {
            request.setAttribute("zapisky", zapisky);
            request.getRequestDispatcher("/WEB-INF/view/zapisky.jsp").forward(request, response);
        } else if (adresa.equals("/upravit")) {
            int index = Integer.parseInt(request.getParameter("index"));
            if (zapisky.get(index) != null) {
                request.setAttribute("zapisek", zapisky.get(index));
                request.getRequestDispatcher("/WEB-INF/view/upravit.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adresa = request.getServletPath();
        request.setCharacterEncoding("UTF-8");

        if (adresa.equals("/pridat")) {
            String nadpis = request.getParameter("nadpis");
            String obsah = request.getParameter("obsah");

            if (!nadpis.isEmpty() && !obsah.isEmpty()) {
                zapisky.add(new Zapisek(nadpis, obsah));
                presmeruj(request, response, "/");
            } else {
                presmeruj(request, response, "/?upozorneni=True");
            }
        } else if (adresa.equals("/ulozitupravy")) {
            int index = Integer.parseInt(request.getParameter("index"));
            String nadpis = request.getParameter("nadpis");
            String obsah = request.getParameter("obsah");

            if (!nadpis.isEmpty() && !obsah.isEmpty()) {
                zapisky.set(index, new Zapisek(nadpis, obsah));
                presmeruj(request, response, "/");
            } else {
                presmeruj(request, response, "/upravit?index=" + index + "&upozorneni=True");
            }
        } else if (adresa.equals("/smazat")) {
            int index = Integer.parseInt(request.getParameter("index"));
            zapisky.remove(index);
            presmeruj(request, response, "/");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void presmeruj(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", request.getContextPath() + url);
    }
}
