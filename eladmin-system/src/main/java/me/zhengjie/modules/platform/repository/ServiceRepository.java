package me.zhengjie.modules.platform.repository;

import me.zhengjie.modules.platform.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author he fei
 * @date 2020-12-23
 **/
public interface ServiceRepository extends JpaRepository<Service, Long>, JpaSpecificationExecutor<Service> {
}