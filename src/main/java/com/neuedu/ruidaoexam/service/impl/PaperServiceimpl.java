package com.neuedu.ruidaoexam.service.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.ruidaoexam.dao.AnsweredPaperMapper;
import com.neuedu.ruidaoexam.dao.PaperMapper;
import com.neuedu.ruidaoexam.dao.TradeRecordMapper;
import com.neuedu.ruidaoexam.entity.Paper;
import com.neuedu.ruidaoexam.entity.PaperExample;
import com.neuedu.ruidaoexam.entity.QuestionBank;
import com.neuedu.ruidaoexam.entity.QuestionBankExample;
import com.neuedu.ruidaoexam.entity.PaperExample.Criteria;
import com.neuedu.ruidaoexam.entity.TradeRecord;
import com.neuedu.ruidaoexam.service.PaperService;
@Service
public class PaperServiceimpl implements PaperService{
	@Autowired
	PaperMapper paperMapper;
	@Autowired
	TradeRecordMapper tradeRecordMapper;
	@Autowired AnsweredPaperMapper answeredpapermapper;
	@Override//向试卷表中插入一条数据，同时返回其id值
	public Integer addPaper(Paper paper) {
		paperMapper.addPaper(paper);
		return paper.getPaperId();
	}
	@Override
	public String getPaper_name(Integer answered_paper_id) {
		Paper paper=paperMapper.selectByPrimaryKey(answeredpapermapper.selectByPrimaryKey(answered_paper_id).getPaperId());
		return paper.getPaperName();
	}
	@Override
	public HashMap<String, Object> getPaperByTeacherId(Integer TeacherId) {
		PaperExample paperExample = new PaperExample();
		Criteria paperCriteria = paperExample.createCriteria();
		paperCriteria.andCreatedbyteacheridEqualTo(TeacherId);
		List<Paper> createpapers = paperMapper.selectByExample(paperExample);
		List<TradeRecord> trades = tradeRecordMapper.getTradesbyTeacherId(TeacherId);
		List<Integer> paperIds = new ArrayList<Integer>();
		for (TradeRecord tradeRecord:trades) {
			if (tradeRecord.getPaperId() != null) {
				paperIds.add(tradeRecord.getPaperId());
				System.out.println(tradeRecord.getPaperId());
			}
		}
		List<Paper> buyPapers = new ArrayList<Paper>();
		for (Integer i : paperIds) {
			Paper paper = paperMapper.selectByPrimaryKey(i);
			buyPapers.add(paper);
		}
		HashMap<String, Object> papers = new HashMap<String,Object>();
		papers.put("createpapers", createpapers);
		papers.put("buypapers", buyPapers);
		for (Paper paper:buyPapers) {
			System.out.println(paper.getPaperId());
		}
//		createpapers.addAll(buyPapers);
//		return createpapers;
		return papers;
		
//		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//		for (TradeRecord trade:trades) {
//			System.out.println(trade.getTime().toString());
//		}
//		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
	}
	//通过老师id查询其创建的题库
		@Override
		public List<Paper> getPapersByTeacherid(Integer TeacherId) {
			PaperExample paperExample = new PaperExample();
			Criteria createCriteria = paperExample.createCriteria();
			createCriteria.andCreatedbyteacheridEqualTo(TeacherId);
			List<Paper> papers = paperMapper.selectByExample(paperExample);
//			for (QuestionBank questionBank:questionBanks) {
//				System.out.println(questionBank.getQuesBankName());
//			}
			return papers;
		}
		@Override
		public int deletePaperByPaperId(Integer PaperId) {
			int rs = paperMapper.deleteByPrimaryKey(PaperId);
			return rs;
		}
		@Override
		public List<Paper> getAllSelledPapers(Integer stuid) {
			PaperExample paperExample = new PaperExample();
			Criteria paperCriteria = paperExample.createCriteria();
			paperCriteria.andPointPriceGreaterThan(0);
			if(tradeRecordMapper.getBoughtPaperIDs(stuid).size()>0) {
			paperCriteria.andPaperIdNotIn(tradeRecordMapper.getBoughtPaperIDs(stuid));
			}
			List<Paper> selledPapers=paperMapper.selectByExample(paperExample);
			return selledPapers;
		}

		@Override
		public List<Paper> getCertainTypeSelledPapers(Integer type,Integer stuid) {
			PaperExample paperExample = new PaperExample();
			Criteria paperCriteria = paperExample.createCriteria();
			paperCriteria.andPointPriceGreaterThan(0);
			paperCriteria.andPaperTypeEqualTo(type);
			if(tradeRecordMapper.getBoughtPaperIDs(stuid).size()>0) {
				paperCriteria.andPaperIdNotIn(tradeRecordMapper.getBoughtPaperIDs(stuid));
			}
			List<Paper> selledPapers=paperMapper.selectByExample(paperExample);
			return selledPapers;
		}

		@Override
		public List<Paper> keywordSearchPaper(String keyword,Integer teacherid){
			PaperExample paperExample = new PaperExample();
			Criteria createCriteria = paperExample.createCriteria();
			createCriteria.andPaperNameLike("%"+keyword+"%");
			createCriteria.andPointPriceGreaterThan(0);
			createCriteria.andCreatedbyteacheridNotEqualTo(teacherid);
			if(tradeRecordMapper.getBoughtBankIDs(teacherid).size()>0) {
				createCriteria.andPaperIdNotIn(tradeRecordMapper.getBoughtBankIDs(teacherid));
				}
			List<Paper> papers = paperMapper.selectByExample(paperExample);
			return papers;
		}
		@Override
		public Paper getPaperByPaperId(Integer paper_id) {
			Paper paper = paperMapper.selectByPrimaryKey(paper_id);
			return paper;
		}
}
