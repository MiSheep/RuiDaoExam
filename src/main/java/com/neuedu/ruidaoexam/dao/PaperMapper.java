package com.neuedu.ruidaoexam.dao;

import com.neuedu.ruidaoexam.entity.Paper;
import com.neuedu.ruidaoexam.entity.PaperExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaperMapper {
    int countByExample(PaperExample example);

    int deleteByExample(PaperExample example);

    int deleteByPrimaryKey(Integer paperId);

    int insert(Paper record);

    int insertSelective(Paper record);

    List<Paper> selectByExample(PaperExample example);

    Paper selectByPrimaryKey(Integer paperId);

    int updateByExampleSelective(@Param("record") Paper record, @Param("example") PaperExample example);

    int updateByExample(@Param("record") Paper record, @Param("example") PaperExample example);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
    //添加卷子并返回新添加卷子的id值
    int addPaper(Paper paper);
    
    int cancelSellPaper(Integer paperid,Integer teacherid);//取消贩卖卷子
    
    int sellPaper(Integer paperid,Integer price,Integer teacherid);//贩卖卷子
}