package com.dream11.fantasy.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream11.fantasy.model.TeamsEntity;

@Repository
public interface TeamsEntityRepo extends JpaRepository<TeamsEntity, Integer> {
   
    
    
//    @Query("SELECT a FROM Arcust a WHERE a.arcustno<='?1' ORDER BY a.arcustno DESC")
//    List<Arcust> findByTop(String arcustno, Pageable pageable);


}
