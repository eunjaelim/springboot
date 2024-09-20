package hellojpa;

import sun.jvm.hotspot.code.CompressedStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            // mapped by 된거에서는 읽기만 가능하고 변경을 불가하다
//          team.getMembers().add(member);
            em.persist(team);


            Member member = new Member();
            // 연간관계 주인에서만 값을 변경할 수 있다.
            member.setUsername("member1");
            em.persist(member);

            team.addMember(member);

            // 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정해야함
            // team.getMembers().add(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member m : members){
                System.out.println("m = " + m.getUsername());
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
