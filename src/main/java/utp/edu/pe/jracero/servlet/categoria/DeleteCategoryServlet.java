package utp.edu.pe.jracero.servlet.categoria;

import utp.edu.pe.jracero.dao.CategoriaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "deleteCategory", urlPatterns = {"/deleteCategory"})
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_categoria = Integer.parseInt(req.getParameter("id_categoria"));
        CategoriaDao categoriaDao;

        try {
            categoriaDao = new CategoriaDao();
            categoriaDao.deleteCategoria(id_categoria);
            categoriaDao.close();

            ErrorLog.log("Categoria eliminada con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
