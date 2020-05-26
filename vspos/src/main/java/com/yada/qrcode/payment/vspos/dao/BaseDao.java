package com.yada.qrcode.payment.vspos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 基础Dao，继承了 {@link CrudRepository} {@link JpaRepository} {@link JpaSpecificationExecutor}
 * 需要两个泛型参数，第一个是实体类，第二个是实体主键
 *
 * @author quhan
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends CrudRepository<T, ID>, JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
