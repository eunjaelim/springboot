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


            Member1 member = new Member1();
            member.setUsername("member1");
            em.persist(member);

            em.flush();
            em.clear();

            java.util.List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member1 m", MemberDTO.class)
                    .getResultList();


            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

