package com.yaoqian.mini_alipay.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionMapper {
    /*******查询所有账单数据********/
    @Select("select * from transaction_info")
    public List<TransactionEntity> QueryTransByAll();

    /*******根据uid查询所有账户********/
    @Select("select * from transaction_info where trans_uid = #{arg0}")
    public List<TransactionEntity> QueryTranslistByTime(String uid);

    /*******根据月份查询账单********/
    @Select("select * from transaction_info where trans_uid = #{arg0} and trans_year =#{arg1} and trans_month =#{arg2} and trans_type in ${arg3}")
    public List<TransactionEntity> QueryTranslistByTime_y(String uid,int year,int month,String trans_type);
    /*******根据月份查询账单********/
    @Select("select * from transaction_info where trans_uid = #{arg0} and trans_time>= #{arg1} and trans_time <= #{arg2} and trans_type in ${arg3}")
    public List<TransactionEntity> QueryTranslistByTime_d(String uid,String time_start,String time_end,String trans_type);

    /*******查询账单详细********/
    @Select("select * from transaction_info where trans_id = #{arg0}")
    public TransactionEntity QueryTransDetail( Integer Trans_id);

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
