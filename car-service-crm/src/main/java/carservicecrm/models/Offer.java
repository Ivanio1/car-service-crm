package carservicecrm.models;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = false)
    private String name;
    private String description;
    private Integer price;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "offer")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;


    @ManyToMany(mappedBy = "offers")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Review> reviews = new HashSet<>();

    public void addReview(Review review) {
        reviews.add(review);
        review.getOffers().add(this);
    }

    public void removeReview(Review review) {
        try{
            reviews.remove(review);
            review.getOffers().remove(this);
        }catch (Exception ignored){

        }

    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getPreviewImageId() {
        return previewImageId;
    }
    public void addImageToOffer(Image image) {
        image.setOffer(this);
        images.add(image);
    }
    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
