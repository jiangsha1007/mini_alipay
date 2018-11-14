package com.yaoqian.mini_alipay.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.yaoqian.mini_alipay.entity.TransactionEntity;

public interface TransactionMapper {
    /*******查询所有账单数据********/
    @Select("select * from transaction_info")
    public List<TransactionEntity> QueryTransByAll();

    /*******根据id查询符合用户********/
    @Select("select * from transaction_info where trans_uid = #{uid}")
    public List<TransactionEntity> QueryTranslistByTime(String uid);

    /*******添加新用户********/
    @Insert("insert into user(name,age,phone) values (#{name},#{age},#{phone})")
    public int addUser(TransactionEntity user);

    /*******修改已有用户信息********/
    @Update("update user set name=#{name},age=#{age},phone=#{phone} where id=#{id}")
    public int updateUser(TransactionEntity user);

    /*******根据id删除用户********/
    @Delete("delete from user where id=#{id}")
    public int deleteUser(int id);

}
