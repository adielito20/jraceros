package utp.edu.pe.jracero.servlet.categoria;

import utp.edu.pe.jracero.dao.CategoriaDao;
import utp.edu.pe.jracero.model.Categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "categories", urlPatterns = {"/categories"})
public class CategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoriaDao categoriaDao;
        List<Categoria> categorias;

        try {
            categoriaDao = new CategoriaDao();
            categorias = categoriaDao.getCategorias();
            categoriaDao.close();
            req.setAttribute("categorias", categorias);

            ErrorLog.log("Categorias obtenidas con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("categoria.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
