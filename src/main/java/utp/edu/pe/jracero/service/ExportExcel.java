package utp.edu.pe.jracero.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

@WebServlet(name = "exportExcel", urlPatterns = {"/exportExcel"})
public class ExportExcel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar la sesión
        /**
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Reserva> reservas = new ArrayList<> ();

        try {
            ReservaDAO reservaDAO = new ReservaDAO();
            reservas = reservaDAO.getReservas();
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        // Configurar el tipo de contenido y la cabecera del archivo
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"historial_reservas.xlsx\"");

        // Crear un libro de trabajo de Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Historial de Reservas");

        // Crear una fila de encabezado
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Id", "Cliente", "Habitación", "Fecha de ingreso", "Fecha de salida", "Monto pagado", "Método de pago", "Estado"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook)); // Estilo para el encabezado
        }

        // Agregar datos de reservas
        int rowNum = 1;
        if (reservas != null && !reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(reserva.getId_reserva());
                row.createCell(1).setCellValue(reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido());
                row.createCell(2).setCellValue(reserva.getHabitacion().getNumero_habitacion());
                row.createCell(3).setCellValue(reserva.getFecha_ingreso().toString());
                row.createCell(4).setCellValue(reserva.getFecha_salida().toString());
                row.createCell(5).setCellValue(reserva.getMonto_total());
                row.createCell(6).setCellValue(reserva.getMetodo_pago());
                row.createCell(7).setCellValue(reserva.getEstado_reserva());
            }
        }

        // Auto ajustar columnas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir el libro de trabajo en la respuesta
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }

        // Cerrar el libro de trabajo
        workbook.close();
        */
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}

