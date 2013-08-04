package servlety;

import databaze.UpravaZapisku;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modely.Zapisek;

@WebServlet(name = "Controller", urlPatterns = {"/zapisky", "/upravit", "/pridat", "/smazat", "/ulozitupravy"})
@ServletSecurity(
    @HttpConstraint(rolesAllowed = {"user"}))
public class Controller extends HttpServlet {   
    
    private final static Logger LOGGER = Logger.getLogger(Controller.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adresa = request.getServletPath();
        UpravaZapisku upravaZapisku = new UpravaZapisku();
        String uzivatel = request.getRemoteUser();
        
        if(adresa.equals("/zapisky")) {
            try {
                List<Zapisek> zapisky = upravaZapisku.getZapisky(uzivatel);
                request.setAttribute("zapisky", zapisky);
                request.getRequestDispatcher("/WEB-INF/view/zapisky.jsp").forward(request, response);    
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }            
        }
        else if(adresa.equals("/upravit")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Zapisek zapisek = upravaZapisku.getZapisek(id, uzivatel);
                request.setAttribute("zapisek", zapisek);
                request.getRequestDispatcher("/WEB-INF/view/upravit.jsp").forward(request, response);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String adresa = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        UpravaZapisku upravaZapisku = new UpravaZapisku();
        String uzivatel = request.getRemoteUser();
        
        if(adresa.equals("/pridat")) {
            String nadpis = request.getParameter("nadpis");
            String obsah = request.getParameter("obsah");            
            if(!nadpis.isEmpty() && !obsah.isEmpty()){
                try {
                    upravaZapisku.addZapisek(nadpis, obsah, uzivatel);
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
                presmeruj(request, response, "/"); 
            }
            else {
                LOGGER.info("Proběhl pokus o přidání nevyplněného zápisku.");
                presmeruj(request, response, "/?upozorneni=True"); 
            }           
        }
        else if(adresa.equals("/ulozitupravy")){
            int id = Integer.parseInt(request.getParameter("id"));
            String nadpis = request.getParameter("nadpis");
            String obsah = request.getParameter("obsah");            
            if(!nadpis.isEmpty() && !obsah.isEmpty()){
                try {
                    upravaZapisku.setZapisek(id, nadpis, obsah, uzivatel);
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
                presmeruj(request, response, "/");
            }
            else {
                presmeruj(request, response, "/upravit?index=" + id + "&upozorneni=True");
            }
        }
        else if(adresa.equals("/smazat")){
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                upravaZapisku.removeZapisek(id, uzivatel);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            presmeruj(request, response, "/"); 
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }       
    }

    private void presmeruj(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", request.getContextPath() + url);
    }
}
