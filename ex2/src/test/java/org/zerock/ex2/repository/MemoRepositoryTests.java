package org.zerock.ex2.repository;

import com.sun.jna.platform.linux.Mman;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;
import org.springframework.data.domain.Pageable.*;


import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.springframework.data.domain.PageRequest.*;

import org.zerock.ex2.entity.Memo;
import org.zerock.ex2.repository.MemoRepository;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }
   // 추가
    @Test
    public void testInsertDummies(){
        IntStream.range(1,101).forEach(i->{
            Memo memo = Memo.builder()
                    .memoText("Sample..."+i)
                    .build();
            memoRepository.save(memo);
        });
    }

    // 조회
    @Test
    public void testSelect(){
        // 데이터베이스에 존재하는 mno
        Long mno = 7L;


        Optional<Memo> result  = memoRepository.findById(mno);

        System.out.println("==================================");

        if(result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    // 수정
    @Test
    public void testUpdate(){
        Memo memo = Memo
                .builder()
                .mno(100L)
                .memoText("Update Text")
                .build();

        System.out.println(memoRepository.save(memo));


    }

    @Test
    public  void testDelete(){
        Long mno = 7L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){
        // 1페이지 10개
        Pageable pageable = PageRequest.of(0, 10); // 첫 번째 페이지, 페이지당 10개의 데이터
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("---------------------------------------------");

        // 총 몇페이지
        System.out.println("Total Pages: " + result.getTotalPages());

        // 전체 개수
        System.out.println("Total Count: " + result.getTotalElements());

        // 현재 페이지 번호
        System.out.println("Page Number: " + result.getNumber());

        // 페이지당 데이터 개수
        System.out.println("Page Size: " + result.getSize());

        // 다음 페이지
        System.out.println("has next page?: " + result.hasNext()); //

        // 시작 페이지(0) 여부
        System.out.println("first page?" + result.isFirst());
    }

    // 페이지 역순 정렬
    @Test
    public void testSort() {
        // 1. mno 기준으로 내림차순 정렬
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });

        // 2. mno 내림차순 + memoText 오름차순 정렬
        Sort sort2 = Sort.by("mno").descending();
        Sort sort3 = Sort.by("memoText").ascending();
        Sort sortAll = sort2.and(sort3);  // 두 개의 정렬 기준 결합

        Pageable pageable2 = PageRequest.of(0, 10, sortAll);

        Page<Memo> result2 = memoRepository.findAll(pageable2);

        result2.get().forEach(memo -> {
            System.out.println(memo);
        });
    }


    @Test
    public void testQueryMethods(){
        List<Memo> list = memoRepository.
                findByMnoBetweenOrderByMnoDesc(70L, 80L);

                for (Memo memo : list){
                    System.out.println(memo);
                }
    }


    @Test
    public void testQueryMethodPagable(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(memo -> System.out.println(memo));

    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods(){
        memoRepository.deleteMemoByMnoLessThan(10L);
    }







}
