package com.stayeasy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stayeasy.entity.HostelLocation;

import jakarta.transaction.Transactional;
@Repository
public interface HostelLocationDao extends JpaRepository<HostelLocation, Long>{
	  @Modifying
	    @Transactional
	@Query(value="insert into hostel_locations(location) values(ST_GeomFromText(:point,4326))",nativeQuery =true)
int  insertHostelLocation(@Param("point") String point);
	  
	  @Query(value = "SELECT * FROM hostel_locations ORDER BY hostel_location_id DESC LIMIT 1", nativeQuery = true)
	  Optional<HostelLocation> findLastInsertedLocation();
}


//@Query(value = "INSERT INTO hostel_locations(location) VALUES (ST_GeomFromText(:point, 4326))", nativeQuery = true)
//int insertHostelLocation(@Param("point") String point);