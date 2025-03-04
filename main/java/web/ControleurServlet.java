package web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.IMetierCatalogue;
import metier.MetierImpl;
import metier.Produit;
import web.ProduitModele;

@WebServlet (name="cs",urlPatterns= {"/controleur"})
public class ControleurServlet extends HttpServlet {
private IMetierCatalogue metier;
@Override
public void init() throws ServletException {
metier=new MetierImpl();
}
@Override
protected void doPost(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String mc=request.getParameter("motCle");
ProduitModele mod = new ProduitModele();
mod.setMotCle(mc);
List<Produit> prods = metier.getProduitsParMotCle(mc);
mod.setProduits(prods);
request.setAttribute("modele", mod);
request.getRequestDispatcher("ProduitsView.jsp").forward(request,response);
}
}