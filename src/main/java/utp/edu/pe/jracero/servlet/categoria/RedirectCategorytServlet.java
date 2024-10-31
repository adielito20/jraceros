package utp.edu.pe.jracero.servlet.categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.CategoriaDao;
import utp.edu.pe.jracero.model.Categoria;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import utp.edu.pe.jracero.util.ErrorLog;


@WebServlet(name = "redirectCategory", urlPatterns = {"/redirectCategory"})
public class RedirectCategorytServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        int id_categoria = Integer.parseInt(req.getParameter("id_categoria"));
        CategoriaDao categoriaDao;

        try {
            categoriaDao = new CategoriaDao();
            Categoria categoria = categoriaDao.getCategoriaById(id_categoria);
            categoriaDao.close();
            req.setAttribute("categoria", categoria);

            ErrorLog.log("Categoria redireccionada con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-categoria.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
