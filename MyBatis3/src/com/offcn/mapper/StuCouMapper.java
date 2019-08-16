package com.offcn.mapper;

import com.offcn.pojo.StuCou;
import com.offcn.pojo.StuCouExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCouMapper {
    int countByExample(StuCouExample example);

    int deleteByExample(StuCouExample example);

    int deleteByPrimaryKey(Integer scid);

    int insert(StuCou record);

    int insertSelective(StuCou record);

    List<StuCou> selectByExample(StuCouExample example);

    StuCou selectByPrimaryKey(Integer scid);

    int updateByExampleSelective(@Param("record") StuCou record, @Param("example") StuCouExample example);

    int updateByExample(@Param("record") StuCou record, @Param("example") StuCouExample example);

    int updateByPrimaryKeySelective(StuCou record);

    int updateByPrimaryKey(StuCou record);
}