package com.xrd.sum.daoUtils;
import com.xrd.sum.MyApp;
import com.xrd.sum.bean.DataBean;
import com.xrd.sum.dao.DaoSession;
import com.xrd.sum.dao.DataBeanDao;

import java.util.List;

/**
 * Created by user on 2018/8/14.
 */

public class SQLiteUtils {
    private static SQLiteUtils instance;
    private final DaoSession daoSession;
    private final DataBeanDao dataBeanDao;

    private SQLiteUtils(){
        daoSession = MyApp.getInstances().getDaoSession();
        dataBeanDao = daoSession.getDataBeanDao();
    }
    public static SQLiteUtils getInstance(){
        if(instance==null){
            synchronized (SQLiteUtils.class){
                if(instance==null){
                    instance=new SQLiteUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 添加user
     * @param dataBean
     * @return
     */
    public long addData(DataBean dataBean){
        long insert = dataBeanDao.insert(dataBean);
        return insert;
    }
    /**
     * 删除指定user
     * @param dataBean
     * @return
     */
    public void deleteData(DataBean dataBean){
        dataBeanDao.delete(dataBean);
    }
    /**
     * 更新指定user
     * @param dataBean
     * @return
     */
    public void updataDate(DataBean dataBean){
        dataBeanDao.update(dataBean);
    }

    /**
     * 查询所有
     * @return
     */
    public List<DataBean> findAll(){
        List<DataBean> userList = dataBeanDao.queryBuilder().list();
        return userList;
    }

    /**
     * 按照name进行指定查询
     * @param name
     * @return
     */
    public List<DataBean> selectName(String name){
        List<DataBean> list = dataBeanDao.queryBuilder().where(DataBeanDao.Properties.Name.eq(name)).build().list();
        return list;
    }
    /**
     * 按照age进行查询
     */
    /*public List<User> selectAge(int age){
        userDao.queryBuilder().where(UserDao.Properties.Age==age).build().list();
    }*/

    /**
     * 删除所有
     */
    public void deleteAll(){
        dataBeanDao.deleteAll();
    }

}
