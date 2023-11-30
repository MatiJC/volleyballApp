package com.maticuad.volleyballApp.repositories;

import com.maticuad.volleyballApp.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TeamRepository extends JpaRepository<Team, Long> {
}
