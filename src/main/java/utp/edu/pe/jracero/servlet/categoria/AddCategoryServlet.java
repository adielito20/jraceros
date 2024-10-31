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

@WebServlet(name = "addCategory", urlPatterns = {"/addCategory"})
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        CategoriaDao categoriaDao;

        try {
            categoriaDao = new CategoriaDao();
            categoriaDao.createCategoria(new Categoria(nombre));
            categoriaDao.close();

            ErrorLog.log("Categoria creada con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
