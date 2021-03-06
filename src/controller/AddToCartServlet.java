package controller;

import dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/books";
        CartDAO dao = new CartDAO();
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        dao.addCart(bookId);

        resp.sendRedirect(req.getContextPath() + url);
    }
}
