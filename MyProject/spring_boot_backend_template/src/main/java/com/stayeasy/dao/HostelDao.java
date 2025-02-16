package com.stayeasy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stayeasy.entity.Hostel;
import com.stayeasy.entity.HostelLocation;

@Repository
public interface HostelDao extends JpaRepository<Hostel, Long> {
	
	@Query(value="select hostel_id from hostel where owner_id=:ownerId",nativeQuery = true)
	List<Long> getlist(@Param("ownerId") Long ownerId);
//	@Query(value = "SELECT h.* FROM hostel h " +
//            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
//            "WHERE ST_Distance_Sphere(hl.location, ST_GeomFromText(:point, 4326)) <= 5000",
//    nativeQuery = true)
	
//List<Object[]> findHostelsByLocation(@Param("point") String point);
//	@Query(value = "SELECT h.*, " +
//            "ST_Distance_Sphere(hl.location, ST_GeomFromText(:point, 4326)) AS distance " +
//            "FROM hostel h " +
//            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
//            "WHERE ST_Distance_Sphere(hl.location, ST_GeomFromText(:point, 4326)) <= 5000",
// nativeQuery = true)
//List<Object[]> findHostelsByLocation(@Param("point") String point);
//	@Query(value = "SELECT h.hostel_id, h.hostelname, h.address, hl.location, " +
//            "ST_Distance_Sphere(ST_GeomFromText(:currentPoint, 4326), " +
//            "ST_GeomFromText(:searchedPoint, 4326)) as distance " +
//            "FROM hostel h " +
//            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
//            "WHERE ST_Distance_Sphere(ST_GeomFromText(:currentPoint, 4326), " +
//            "ST_GeomFromText(:searchedPoint, 4326)) <= 5000",
//    nativeQuery = true)
//List<Object[]> findHostelsByLocation(@Param("currentPoint") String currentPoint, 
//                                  @Param("searchedPoint") String searchedPoint);
	
	@Query(value = "SELECT h.hostel_id, h.hostelname, h.address, hl.location, " +
            "ST_Distance_Sphere(ST_GeomFromText(:currentPoint, 4326), hl.location) as distance " + // ✅ Distance from user to hostel
            "FROM hostel h " +
            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
            "WHERE ST_Distance_Sphere(ST_GeomFromText(:searchedPoint, 4326), hl.location) <= 5000", // ✅ Filter hostels under 5km from searched location
    nativeQuery = true)
List<Object[]> findHostelsByLocation(@Param("currentPoint") String currentPoint, 
                                     @Param("searchedPoint") String searchedPoint);
	
	@Query(value = "SELECT h.hostel_id, h.hostelname, h.address, hl.location, " +
            "ST_Distance_Sphere(hl.location, ST_GeomFromText(:point, 4326)) as distance " +
            "FROM hostel h " +
            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
            "WHERE ST_Distance_Sphere(hl.location, ST_GeomFromText(:point, 4326)) <= 5000",
    nativeQuery = true)
List<Object[]> findHostelsByLocation(@Param("point") String point);
	
	@Query(value = """
		    SELECT h.hostel_id, h.hostelname, h.address, hl.location, 
		           ST_Distance_Sphere(hl.location, ST_GeomFromText(:userLocation, 4326)) AS distance
		    FROM hostel h
		    JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id
		    WHERE ST_Distance_Sphere(hl.location, ST_GeomFromText(:userLocation, 4326)) <= 5000
		    ORDER BY distance ASC
		""", nativeQuery = true)
		List<Object[]> findNearbyHostels(@Param("userLocation") String userLocation);
	
//	@Query(value = "SELECT h.* FROM hostel h " +
//            "JOIN hostel_locations hl ON h.location_id = hl.hostel_location_id " +
//            "WHERE ST_Distance_Sphere(hl.location, ST_GeomFromText(CONCAT('POINT(', :longitude, ' ', :latitude, ')'), 4326)) <= 5000",
//    nativeQuery = true)
//List<Hostel> findHostelsByLocation(@Param("latitude") double lat, 
//                                @Param("longitude") double lng);
                                



}
	
	
	
	
//    @Query(value = "SELECT h.* FROM hostel h " +
//             "JOIN hostel_location hl ON h.location_id = hl.hostel_location_id " +
//             "WHERE ST_Distance_Sphere(hl.location, POINT(:longitude, :latitude)) <= 5000", 
//             nativeQuery = true)
//    List<Hostel> findHostelsByLocation(@Param("latitude") double lat, @Param("longitude") double lng);
//}

//	  @Query(value = "SELECT * FROM hostel h " +
//              "WHERE (6371 * ACOS(COS(RADIANS(:lat)) * COS(RADIANS(h.latitude)) * " +
//              "COS(RADIANS(h.longitude) - RADIANS(:lng)) + SIN(RADIANS(:lat)) * " +
//              "SIN(RADIANS(h.latitude)))) <= 5", nativeQuery = true)
//List<Hostel> findHostelsByLocation(@Param("lat") double lat, @Param("lng") double lng);

