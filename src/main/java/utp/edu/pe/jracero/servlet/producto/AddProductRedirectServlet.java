package utp.edu.pe.jracero.servlet.producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.CategoriaDao;
import utp.edu.pe.jracero.model.Categoria;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddProductRedirectServlet", urlPatterns = {"/addProductRedirect"})
public class AddProductRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CategoriaDao categoriaDao;
        List<Categoria> categorias;

        try {
            categoriaDao = new CategoriaDao();
            categorias = categoriaDao.getCategorias();
            req.setAttribute("categorias", categorias);
            categoriaDao.close();

            ErrorLog.log("Categorias obtenidas correctamente", ErrorLog.Level.INFO);
            req.getRequestDispatcher("add-producto.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
