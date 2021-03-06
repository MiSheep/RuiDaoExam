package com.neuedu.ruidaoexam.dao;

import com.neuedu.ruidaoexam.entity.EssayQuestion;
import com.neuedu.ruidaoexam.entity.JudgeQuestion;
import com.neuedu.ruidaoexam.entity.JudgeQuestionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface JudgeQuestionMapper {
    int countByExample(JudgeQuestionExample example);

    int deleteByExample(JudgeQuestionExample example);

    int deleteByPrimaryKey(Integer judgeId);

    int insert(JudgeQuestion record);

    int insertSelective(JudgeQuestion record);

    List<JudgeQuestion> selectByExample(JudgeQuestionExample example);

    JudgeQuestion selectByPrimaryKey(Integer judgeId);

    int updateByExampleSelective(@Param("record") JudgeQuestion record, @Param("example") JudgeQuestionExample example);

    int updateByExample(@Param("record") JudgeQuestion record, @Param("example") JudgeQuestionExample example);

    int updateByPrimaryKeySelective(JudgeQuestion record);

    int updateByPrimaryKey(JudgeQuestion record);
  //根据在list中的id值选取问题
    List<JudgeQuestion> selectByidInList(List<Integer> judge_id_list);
    
    int insertAndGetId(JudgeQuestion record);
    
    List<JudgeQuestion> getJudgeQuestionsInList(List<Integer> judge_id_list);
}