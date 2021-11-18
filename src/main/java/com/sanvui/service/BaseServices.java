package com.sanvui.service;

import com.sanvui.dao.Imp.BaseDaoImp;
import com.sanvui.dao.ServicesDao;
import com.sanvui.utils.ValidatorBean;
import com.sanvui.utils.ValidatorUtils;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-2:02 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class BaseServices<T> implements ServicesDao<T> {

    private Class<T> classTyper;

    public BaseServices(Class<T> classTyper) {
        this.classTyper = classTyper;
    }


    /*
     * get all data in database
     * return List Object
     * */
    @Override
    public List<T> findAll() {
        BaseDaoImp imp = new BaseDaoImp(this.classTyper);
        return imp.findAll();
    }

    /*
     * get a object database
     * return a Object
     * */
    @Override
    public T findById(int id) {
        BaseDaoImp imp = new BaseDaoImp(this.classTyper);;
        return (T) imp.findById(id);
    }

    /*
     * Insert data to database
     * parameter: List Object
     * put data to insert
     * if validator false return false
     * else continues put data to insert
     * */
    @Override
    public boolean insert(List<T> list) {
        BaseDaoImp imp = new BaseDaoImp(this.classTyper);
        for(T  t : list){
            /*
            * check validator
            * if != success return false
            * else call insert
            * */
            ValidatorBean validatorBean = ValidatorUtils.validatorBean(t);
            if (!validatorBean.isSuccess()){
                System.out.println(validatorBean);
                return false;
            }
        }
//        call insert
        return imp.insert(list);
    }

    /*
     * Delete data by Id database
     * parameter: int id
     * */
    @Override
    public boolean delete(int id) {
        BaseDaoImp imp = new BaseDaoImp(this.classTyper);
        return imp.delete(id);
    }

    /*
     * Update data
     * parameter: List Object
     * if validator false return false
     * else continues put data to update
     * */
    @Override
    public boolean update(List<T> list) {
        BaseDaoImp imp = new BaseDaoImp(this.classTyper);
        for(T  t : list){
            /*
             * check validator
             * if != success return false
             * else go to update to database
             * */
            ValidatorBean validatorBean = ValidatorUtils.validatorBean(t);
            if (!validatorBean.isSuccess()){
                System.out.println(validatorBean);
                return false;
            }
        }
        return imp.update(list);
    }


}
