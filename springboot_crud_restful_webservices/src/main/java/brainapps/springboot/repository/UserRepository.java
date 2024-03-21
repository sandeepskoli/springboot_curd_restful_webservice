package brainapps.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brainapps.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
