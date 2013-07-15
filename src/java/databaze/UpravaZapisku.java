package databaze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modely.Zapisek;

public class UpravaZapisku {

    private Connection getConnection() throws NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context ctx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) ctx.lookup("jdbc/mysql");
        return (ds.getConnection());
    }

    public List<Zapisek> getZapisky() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Zapisek> zapisky = new ArrayList();

        try {
            String query = "SELECT * FROM zapisky";
            connection = getConnection();
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                zapisky.add(new Zapisek(rs.getInt("id"), rs.getString("nadpis"), rs.getString("obsah")));
            }
        } catch (NamingException ex) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sQLException) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
        return zapisky;
    }

    public Zapisek getZapisek(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zapisek zapisek = null;

        try {
            String query = "SELECT * FROM zapisky WHERE id = ?";
            connection = getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                zapisek = new Zapisek(rs.getInt("id"), rs.getString("nadpis"), rs.getString("obsah"));
            }
        } catch (NamingException ex) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sQLException) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
        return zapisek;
    }

    public void setZapisek(int id, String nadpis, String obsah) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE zapisky SET nadpis = ?, obsah = ? WHERE id = ?";
            connection = getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nadpis);
            stmt.setString(2, obsah);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sQLException) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
    }

    public void addZapisek(String nadpis, String obsah) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO zapisky (nadpis, obsah) VALUES (?, ?)";
            connection = getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nadpis);
            stmt.setString(2, obsah);
            stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sQLException) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
    }

    public void removeZapisek(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String query = "DELETE FROM zapisky WHERE id = ?";
            connection = getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException sQLException) {
            Logger.getLogger(UpravaZapisku.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
    }
}
