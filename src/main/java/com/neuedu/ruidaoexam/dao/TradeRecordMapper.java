package com.neuedu.ruidaoexam.dao;

import com.neuedu.ruidaoexam.entity.TradeRecord;
import com.neuedu.ruidaoexam.entity.TradeRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TradeRecordMapper {
    int countByExample(TradeRecordExample example);

    int deleteByExample(TradeRecordExample example);

    int deleteByPrimaryKey(Integer tradeId);

    int insert(TradeRecord record);

    int insertSelective(TradeRecord record);

    List<TradeRecord> selectByExample(TradeRecordExample example);

    TradeRecord selectByPrimaryKey(Integer tradeId);

    int updateByExampleSelective(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByExample(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByPrimaryKeySelective(TradeRecord record);

    int updateByPrimaryKey(TradeRecord record);
    //通过教师id查询其购买记录
    List<TradeRecord> getTradesbyTeacherId(Integer Teacherid);
    
    List<Integer> getBoughtPaperIDs(Integer stuid);//0628 lb 检索已经买的卷子id
    
    List<Integer> getBoughtBankIDs(Integer teacherid);//0628 lb 检索已买的题库id
}