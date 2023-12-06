package com.maticuad.volleyballApp.repositories;

import com.maticuad.volleyballApp.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.Optional;


@RepositoryRestResource
public interface TeamRepository extends JpaRepository<Team, Long> {
    @NonNull
    Optional<Team> findById(@NonNull Long id);
    Optional<Team> findByTeamName(String teamName);


}
