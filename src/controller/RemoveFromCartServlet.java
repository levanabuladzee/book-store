package controller;

import dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/remove-from-cart"})
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/cart";
        CartDAO dao = new CartDAO();
        int cartId = Integer.parseInt(req.getParameter("cartId"));
        dao.removeBook(cartId);

        resp.sendRedirect(req.getContextPath() + url);
    }
}
