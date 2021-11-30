package com.sanvui.repository;

import com.sanvui.model.entity.Footer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: VuiSK
 * @created: 23/11/2021-3:04 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface FooterRepository extends JpaRepository<Footer, Integer> {
}
