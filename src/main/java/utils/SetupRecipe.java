package utils;


import entities.Recipe;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SetupRecipe {

  //test if i can create a recipe with a user without using any relations functions.
  public static void main(String[] args) {

  test();
  }
  public static void test() {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    User user = em.find(User.class, "user");
    Recipe recipe = new Recipe("recipe_test", "description_test", user);
  //  user.addRecipe(recipe);

    em.getTransaction().begin();
    em.persist(recipe);
   // em.merge(user);

    em.getTransaction().commit();
    System.out.println("Created TEST Recipe");
  }

  public static void test2() {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    User user = em.find(User.class, "user");


    System.out.println(user.toString());
    System.out.println(user.getRecipes().toString());
    System.out.println(user.getRecipes().size());
    System.out.println(user.getRecipes().get(0).getName());

  }
}
