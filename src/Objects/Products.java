package Objects;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Products {

    @XmlElement
    private String user;

    @XmlElement(name="product")
    private List<Product> products = new ArrayList<>();

    public void addToList(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {return products; }

    public void setUser(String user) { this.user = user; }
}
