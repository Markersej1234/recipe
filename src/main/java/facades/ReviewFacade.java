package facades;

import dtos.ReviewDTO;
import entities.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReviewFacade {

    public ReviewFacade() {
    }

    private static ReviewFacade instance;
    private static EntityManagerFactory emf;

    public static ReviewFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ReviewFacade();
        }
        return instance;
    }

    public List<ReviewDTO> getAllReviews() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r", Review.class);
        List<Review> rms = query.getResultList();
        return ReviewDTO.getDtos(rms);
    }

    public ReviewDTO createReview(ReviewDTO pn) {
        Review review = new Review(pn.getDescription());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ReviewDTO(review);
    }

    public static ReviewFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ReviewFacade();
        }
        return instance;
    }



}