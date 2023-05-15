package facades;

import dtos.IngredientDTO;
import dtos.InstructionDTO;
import dtos.RenameMeDTO;
import entities.Ingredient;
import entities.Instruction;
import entities.RenameMe;
import errorhandling.NotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class IngredientFacade {

    private static IngredientFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private IngredientFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IngredientFacade getIngredientFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new IngredientFacade();
        }
        return instance;
    }

    public List<IngredientDTO> getAllIngredients(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i", Ingredient.class);
        List<Ingredient> ingredients = query.getResultList();
        return IngredientDTO.getDtos(ingredients);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Ingredient update(Ingredient ingredient) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Ingredient found = em.find(Ingredient.class, ingredient.getId());
        if (found == null) {
            throw new NotFoundException("Could not find ingredient with id: " + ingredient.getId());
        }
        try {
            em.getTransaction().begin();
            Ingredient updated = em.merge(ingredient);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getQuantity(), ingredientDTO.getMeasurementUnit());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ingredient);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new IngredientDTO(ingredient);
    }
}