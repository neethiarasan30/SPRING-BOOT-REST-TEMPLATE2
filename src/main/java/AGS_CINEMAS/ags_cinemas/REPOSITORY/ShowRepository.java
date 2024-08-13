package AGS_CINEMAS.ags_cinemas.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AGS_CINEMAS.ags_cinemas.ENTITY.ShowEntity;


@Repository
public interface ShowRepository extends JpaRepository<ShowEntity,Integer>{

}
