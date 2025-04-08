package co.za.vendor.simpleIntrestCalculator_app.repository;

import co.za.vendor.simpleIntrestCalculator_app.model.SI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SI_Repository extends JpaRepository<SI, Integer> {
}
