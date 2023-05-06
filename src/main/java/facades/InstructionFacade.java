package facades;
import dtos.InstructionDTO;
import entities.Instruction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class InstructionFacade {

    public InstructionFacade() {
    }

    private static InstructionFacade instance;
    private static EntityManagerFactory emf;

    public static InstructionFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new InstructionFacade();
        }
        return instance;
    }

    public List<InstructionDTO> getAllInstructions(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Instruction> query = em.createQuery("SELECT i FROM Instruction i", Instruction.class);
        List<Instruction> rms = query.getResultList();
        return InstructionDTO.getDtos(rms);
    }

    //get recipe skal måske sletes
    public InstructionDTO createInstruction(InstructionDTO pn) {
        Instruction instruction = new Instruction(pn.getStepNumber(), pn.getInstructionText(), pn.getRecipe());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(instruction);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new InstructionDTO(instruction);
    }

    public static InstructionFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new InstructionFacade();
        }
        return instance;
    }

    public Instruction deleteInstruction(Long id) {
        EntityManager em = emf.createEntityManager();
        Instruction instruction = em.find(Instruction.class, id);
        try {
            em.getTransaction().begin();
            em.remove(instruction);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return instruction;
    }

    public Instruction updateInstruction(Instruction instruction) {
        EntityManager em = emf.createEntityManager();
        Instruction found = em.find(Instruction.class, instruction.getId());
        if (found == null) {
            throw new IllegalArgumentException("No instruction with provided id found");
        }
        try {
            em.getTransaction().begin();
            Instruction updated = em.merge(instruction);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }


    public InstructionDTO getInstructionById(Long id) {
        EntityManager em = emf.createEntityManager();
        Instruction instruction = em.find(Instruction.class, id);
        if (instruction == null) {
            throw new IllegalArgumentException("No instruction with provided id found");
        }
        return new InstructionDTO(instruction);
    }


}
