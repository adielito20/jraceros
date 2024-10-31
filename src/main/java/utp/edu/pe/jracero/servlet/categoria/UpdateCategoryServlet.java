package utp.edu.pe.jracero.servlet.categoria;

import utp.edu.pe.jracero.dao.CategoriaDao;
import utp.edu.pe.jracero.model.Categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "updateCategory", urlPatterns = {"/updateCategory"})
public class UpdateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id_categoria = Integer.parseInt(req.getParameter("id_categoria"));
        String nombre = req.getParameter("nombre");
        CategoriaDao categoriaDao;

        try {
            categoriaDao = new CategoriaDao();
            categoriaDao.updateCategoria(new Categoria(id_categoria, nombre));
            categoriaDao.close();

            ErrorLog.log("Categoria actualizada con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
