package me.zhengjie.modules.platform.repository;

import me.zhengjie.modules.platform.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author he fei
 * @date 2020-12-26
 **/
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
}