package metier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetierImpl implements IMetierCatalogue{

    @Override
    public List<Produit> getProduitsParMotCle(String mc) {
        List<Produit> prods = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM PRODUITS WHERE NOM_PRODUIT LIKE ?"
            );
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit p = new Produit();
                p.setIdProduit(rs.getLong("ID_PRODUIT"));
                p.setNomProduit(rs.getString("NOM_PRODUIT"));
                p.setPrix(rs.getDouble("PRIX"));
                prods.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prods;
    }

    @Override
    public void addProduit(Produit p) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO PRODUITS (NOM_PRODUIT, PRIX) VALUES (?, ?)"
            );
            ps.setString(1, p.getNomProduit());
            ps.setDouble(2, p.getPrix());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
