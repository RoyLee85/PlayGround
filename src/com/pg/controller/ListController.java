package com.pg.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pg.VO.BoardVO;
import com.pg.dao.BoardDao;
import com.pg.paging.Paging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController {
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(getClass());
	private int pageSize = 5;
	private int blockCount = 5;
		
	@Autowired
	private BoardDao boardDao;

	@RequestMapping({ "/board/list.do" })
	public ModelAndView process(
			@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord) {
		String pagingHtml = "";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		HashMap<String, Object> map = new HashMap();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);

		int count = this.boardDao.getCount(map);

		Paging page = new Paging(keyField, keyWord, currentPage, count,
				this.pageSize, this.blockCount, "list.do");

		pagingHtml = page.getPagingHtml().toString();

		map.put("start", Integer.valueOf(page.getStartCount()));
		map.put("end", Integer.valueOf(page.getEndCount()));

		List<BoardVO> list = null;
		if (count > 0) {
			list = this.boardDao.list(map);
		} else {
			list = Collections.emptyList();
		}
		int number = count - (currentPage - 1) * this.pageSize;
		int endPage = (count / pageSize) + ((count % pageSize == 0) ? 0 : 1);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count", Integer.valueOf(count));
		mav.addObject("currentPage", Integer.valueOf(currentPage));
		mav.addObject("list", list);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("number", Integer.valueOf(number));
		mav.addObject("endPage",endPage);
		mav.addObject("keyField",keyField);
		mav.addObject("keyWord",keyWord);

		return mav;
	}
	
		@RequestMapping(value="/board/insert.do",method=RequestMethod.GET)
		public String insertForm(){
			return "boardInsert";
		}
		
		@SuppressWarnings({ "deprecation", "null" })
		@RequestMapping(value="/board/insert.do",method=RequestMethod.POST)
		public String insert(HttpServletRequest request,HttpServletResponse response){
			
			int fileMaxSize = 10*1024*1024;
			String savePath = request.getRealPath("/upload").replaceAll("\\\\","/");
			MultipartRequest mr;
			try {
				mr = new MultipartRequest(request,savePath,fileMaxSize,"UTF-8",new DefaultFileRenamePolicy());
				String seq1=mr.getParameter("seq");
				String title=mr.getParameter("title");
				String name=mr.getParameter("name");
				String pass=mr.getParameter("pass");
				String content=mr.getParameter("content");
				String orgfilename=mr.getOriginalFileName("uploadFile");
				String savefilename=mr.getFilesystemName("uploadFile");
				if(orgfilename==null){
					orgfilename="";
					savefilename="";
				}
//				String ref1=mr.getParameter("ref");
//				String lev1=mr.getParameter("lev");
//				String step1=mr.getParameter("step");
//				
//				System.out.println("ref 값 : " + ref1);
//				
				int ref=0;
				int lev=0;
				int step=0;
				
				int seq=boardDao.getMaxSeq()+1;
				
				if(!(seq1.equals(""))){
					BoardVO vo2=boardDao.getInfo(Integer.parseInt(seq1));
					String ref1=mr.getParameter("ref");
					ref=Integer.parseInt(ref1);
					String lev1=mr.getParameter("lev");
					lev=Integer.parseInt(lev1);
					String step1=mr.getParameter("step");
					step=Integer.parseInt(step1);
					//vo2.setRef(ref);
					//vo2.setLev(lev);
					//vo2.setStep(step);
					boardDao.stepUp(vo2);
					lev+=1;
					step+=1;
				}else{
					ref=seq;
				}

				BoardVO vo=new BoardVO(seq,name,title,content,pass,orgfilename,savefilename,ref,lev,step);
				boardDao.insert(vo);
				System.out.println(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:list.do";
		}
		
		@RequestMapping(value="/board/detail.do",method=RequestMethod.GET)
		public String detail(int seq,Model model){
			BoardVO vo=boardDao.getInfo(seq);
			String content=vo.getContent();
			content=content.replace("\n", "<br>");
			vo.setContent(content);
			boardDao.addHit(seq);
			model.addAttribute("vo",vo);
			return "boardDetail";
		}
		
		@RequestMapping(value="/board/download.do",method=RequestMethod.GET)
		public void download(int seq,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			BoardVO vo=boardDao.getInfo(seq);
			String orgfilename=vo.getOrgfilename();
			String savefilename=vo.getSavefilename();
			//String filename=URLEncoder.encode(orgfilename,"utf-8");
			String filename="\"" + new String(orgfilename.getBytes("UTF-8"), "8859_1") + "\"";
			response.setContentType("application/octet-stream");
			//orgfilename=new String(orgfilename.getBytes("UTF-8"),"iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			OutputStream os=response.getOutputStream();
			@SuppressWarnings("deprecation")
			String path =request.getRealPath("/upload");
			System.out.println(path);
			FileInputStream fis = new FileInputStream(path + File.separator + savefilename);
			int n= 0;
			byte[] b = new byte[512];
			while((n = fis.read(b))!=-1){
				os.write(b,0,n);
			}
			fis.close();
			os.close();
			}
		
		@RequestMapping(value="/board/delete.do",method=RequestMethod.GET)
		public String delete(HttpServletRequest request,int seq){
			BoardVO vo=boardDao.getInfo(seq);
			String path=request.getRealPath("/upload")+"/"+vo.getSavefilename();
			File file = new File(path);
			System.out.println(path);
				if(file.exists()==true){
					if(file.delete()){
						System.out.println("파일 삭제 성공");
					}
				}
			boardDao.delete(seq);
			return "redirect:/board/list.do";
		}
		
		@RequestMapping(value="/board/deleteFile.do",method=RequestMethod.GET)
		public String boardFileDelete(HttpServletRequest request, int seq){
			BoardVO vo = boardDao.getInfo(seq);
			vo.setOrgfilename("");
			boardDao.updateFilename(vo);
				return "redirect:/board/update.do?seq="+seq;
		}
		
		@RequestMapping(value="/board/update.do",method=RequestMethod.GET)
		public String update(int seq,Model model){
			BoardVO vo=boardDao.getInfo(seq);
			model.addAttribute("vo",vo);
			return "boardUpdate";
		}
		
		@RequestMapping(value="/board/updateOk.do",method=RequestMethod.POST)
		public String boardFileUpload(HttpServletRequest request){
				int fileMaxSize = 10*1024*1024;
				int seq=0;
				String savePath = request.getRealPath("/upload").replaceAll("\\\\","/");
				MultipartRequest mr;
				try {
					mr = new MultipartRequest(request,savePath,fileMaxSize,"UTF-8",new DefaultFileRenamePolicy());
					String seq1=mr.getParameter("seq");
					String title=mr.getParameter("title");
					String name=mr.getParameter("name");
					String content=mr.getParameter("content");
					String orgfilename=mr.getOriginalFileName("uploadFile");
					String savefilename=mr.getFilesystemName("uploadFile");
					String fileDeleteCheck=mr.getParameter("deleteCheck");
					seq = Integer.parseInt(seq1);
					BoardVO vo1 = boardDao.getInfo(seq);
					System.out.println(fileDeleteCheck);
					if(orgfilename==null || orgfilename==""){
						if(fileDeleteCheck.equals("y")){
							String path=request.getRealPath("/upload")+"/"+vo1.getSavefilename();
							File file = new File(path);
							System.out.println(path);
								if(file.exists()==true){
									if(file.delete()){
										System.out.println("파일 삭제 성공");
									};
								}
							orgfilename="";
							savefilename="";
						}else{
							orgfilename=vo1.getOrgfilename();
							savefilename=vo1.getSavefilename();
						}
					}else{	
						String path=request.getRealPath("/upload")+"/"+vo1.getSavefilename();
						File file = new File(path);
						System.out.println(path);
							if(file.exists()==true){
								if(file.delete()){
									System.out.println("파일 삭제 성공");
								};
							}
					}
					System.out.println(name);
					System.out.println(title);
					System.out.println(content);
					System.out.println(orgfilename);
					System.out.println(savefilename);
					System.out.println(seq);
					BoardVO vo=new BoardVO(seq,name,title,content,orgfilename,savefilename);
					int n=boardDao.updateOk(vo);
					
					if(n>0){
						System.out.println("저장 성공 ");
					}else{
						System.out.println("저장 실패 ");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "redirect:detail.do?seq=" + seq;
			}
}
