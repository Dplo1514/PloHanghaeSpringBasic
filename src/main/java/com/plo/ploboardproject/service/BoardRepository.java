package com.plo.ploboardproject.service;
import com.plo.ploboardproject.domain.BoardWrite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardWrite, Long> {
    List<BoardWrite> findAllByOrderByModifiedAtDesc(); //변경사항을 추적해 오름차순 정렬
}
