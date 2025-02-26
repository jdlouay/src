package metier;
import java.util.List;

public interface IMetierCatalogue {
    List<Produit> getProduitsParMotCle(String mc);
    void addProduit(Produit p);
}
