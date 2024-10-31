package jpql;

import hellojpa.Member;

import javax.persistence.*;
import java.util.*;


public class JpaMain {
    public static <List> void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            for (int i = 0; i < 100; i++) {

                Member1 member = new Member1();
                member.setUsername("member" +i);
                member.setAge(i);
                em.persist(member);
            }
            em.flush();
            em.clear();

            java.util.List<Member1> result = em.createQuery("select m from Member1 m order by m.age desc", Member1.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("result.size = " + result.size());
            for (Member1 member1 : result){
                System.out.println("member1 = " + member1);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

