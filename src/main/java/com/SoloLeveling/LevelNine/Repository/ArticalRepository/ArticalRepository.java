package  com.SoloLeveling.LevelNine.Repository.ArticalRepository;
import com.SoloLeveling.LevelNine.Entity.UserEntity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SoloLeveling.LevelNine.Entity.ArticalEntity.Artical;

import java.util.List;


public  interface  ArticalRepository extends  JpaRepository<Artical ,Long>{

    Page<Artical> findByUserId(long userId, Pageable pageable);


}