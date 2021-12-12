package com.sanvui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 02/12/2021-11:10 PM
 * @mailto: sanvankhanh@gmail.com
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findByProductNameLike(String name);
}
