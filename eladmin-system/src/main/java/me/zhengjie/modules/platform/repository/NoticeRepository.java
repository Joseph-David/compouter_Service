package me.zhengjie.modules.platform.repository;

import me.zhengjie.modules.platform.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author he fei
 * @date 2020-12-23
 **/
public interface NoticeRepository extends JpaRepository<Notice, Long>, JpaSpecificationExecutor<Notice> {

    List<Notice> findByUsername(String username);

    Notice findByCreateBy(String creatName);
}