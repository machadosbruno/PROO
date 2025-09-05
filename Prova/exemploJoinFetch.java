import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class TbservicoDAO {
    // ... seu EntityManagerFactory ...

    /**
     * Busca um serviço pelo ID e já inicializa o animal e o cliente associados
     * em uma única consulta para evitar LazyInitializationException.
     *
     * @param id O ID do serviço a ser buscado.
     * @return O objeto Tbservico com Tbanimal e Tbcliente totalmente carregados,
     * ou null se não for encontrado.
     */
    public Tbservico buscarPorIdComAnimalECliente(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            // A nossa query poderosa!
            String jpql = "SELECT s FROM Tbservico s " +
                          "JOIN FETCH s.tbanimal a " +
                          "JOIN FETCH a.tbcliente c " +
                          "WHERE s.id = :id";
            
            TypedQuery<Tbservico> query = em.createQuery(jpql, Tbservico.class);
            query.setParameter("id", id);
            
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}