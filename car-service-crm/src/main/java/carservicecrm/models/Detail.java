package carservicecrm.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detailprovider")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer stock;
    private Integer price;

    @ManyToMany(cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "detailprovider_detail",
            joinColumns = @JoinColumn(name = "detail_id"),
            inverseJoinColumns = @JoinColumn(name = "detailprovider_id"))
    private Set<DetailProvider> providers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<DetailProvider> getProviders() {
        return providers;
    }

    public void setProviders(Set<DetailProvider> providers) {
        this.providers = providers;
    }
}
