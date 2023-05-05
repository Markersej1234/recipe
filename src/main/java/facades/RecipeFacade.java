package facades;

import dtos.RecipeDTO;
import dtos.RenameMeDTO;
import entities.Recipe;
import entities.RenameMe;
import entities.User;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class RecipeFacade {

    private static RecipeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RecipeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RecipeFacade getRecipeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<RecipeDTO> getAllRecipes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
        List<Recipe> rms = query.getResultList();
        return RecipeDTO.getDtos(rms);
    }

    public Recipe create(Recipe recipe) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(recipe);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return recipe;
    }

    public Recipe delete(Long id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        try {
            em.getTransaction().begin();
            em.remove(recipe);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return recipe;
    }

    public Recipe update(Recipe recipe) {
        EntityManager em = emf.createEntityManager();
        Recipe found = em.find(Recipe.class, recipe.getId());
        if (found == null) {
            throw new IllegalArgumentException("No recipe with provided id found");
        }
        try {
            em.getTransaction().begin();
            Recipe updated = em.merge(recipe);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }


    public RecipeDTO getRecipeById(Long id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        if (recipe == null) {
            throw new IllegalArgumentException("No recipe with provided id found");
        }
        return new RecipeDTO(recipe);
    }
}
