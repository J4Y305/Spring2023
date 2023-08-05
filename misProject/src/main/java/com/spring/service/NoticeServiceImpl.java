package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.NoticeFileVO;
import com.spring.domain.NoticeVO;
import com.spring.domain.SearchCriteria;
import com.spring.persistence.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	private NoticeDAO dao;
	
	@Override
	public void register(NoticeVO vo) throws Exception {
		// 1) TestArea 줄바꿈 처리
		String content = vo.getContent().replace("\\r\\n", "<br>");
		vo.setContent(content);
		
		// 2) 공지사항 기본 내용 저장(첨부파일 제외) -> PK인 noticeNo 받아오기
		int noticeNo = dao.adCreate(vo);
		
		// 3) 첨부파일 등록
		// 3-1) 첨부파일 존재 유무 확인
		if(vo.getFiles() != null) {
			// 3-2) 다중 첨부파일 저장
			for(int i=0; i <vo.getFiles().length; i++) {
				NoticeFileVO fVo = new NoticeFileVO();
				fVo.setNoticeNo(noticeNo);
				fVo.setNoticeFileName(vo.getFiles()[i]);
				
				dao.insertFile(fVo);
			}
		}

	}

	@Override
	public NoticeVO read(int noticeNo) throws Exception {
		return dao.read(noticeNo);
	}

	@Override
	public void modify(NoticeVO vo) throws Exception {

			// 1) TestArea 줄바꿈 처리
			String content = vo.getContent().replace("\\r\\n", "<br>");
			vo.setContent(content);
			
			// 2) 공지사항 기본 내용 수정(첨부파일 제외) -> PK인 noticeNo 받아오기
			dao.adUpdate(vo);
			
			// 3) 기존의 첨부 파일 삭제
			dao.deleteFile(vo.getNoticeNo());
			
			// 4) 첨부파일 등록
			// 4-1) 첨부파일 존재 유무 확인
			if(vo.getFiles() != null) {
				// 4-2) 다중 첨부파일 저장
				for(int i=0; i <vo.getFiles().length; i++) {
					NoticeFileVO fVo = new NoticeFileVO();
					fVo.setNoticeNo(vo.getNoticeNo());
					fVo.setNoticeFileName(vo.getFiles()[i]);
					
					dao.insertFile(fVo);
				}
			}

	}

	@Override
	public void remove(int noticeNo) throws Exception {
		// 1) 첨부파일 삭제
		dao.deleteFile(noticeNo);
		// 2) 공지사항 게시글 사제
		dao.adDelete(noticeNo);
	}

	@Override
	public List<NoticeVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public List<NoticeFileVO> fileList(int noticeNo) throws Exception {
		return dao.fileList(noticeNo);
	}

}
