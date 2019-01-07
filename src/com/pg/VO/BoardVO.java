package com.pg.VO;

import java.sql.Date;

public class BoardVO
{
  private int seq;
  private String name;
  private String title;
  private String content;
  private String pass;
  private int hit;
  private Date regdate;
  private String orgfilename;
  private String savefilename;
  private int ref;
  private int lev;
  private int step;
  
  
  
  public BoardVO(){};
  
  public BoardVO(int seq, String name, String title, String content, String pass, String orgfilename, String savefilename, int ref, int lev, int step) {
	super();
	this.seq = seq;
	this.name = name;
	this.title = title;
	this.content = content;
	this.pass = pass;
	this.orgfilename = orgfilename;
	this.savefilename = savefilename;
	this.ref = ref;
	this.lev = lev;
	this.step = step;
}

  public BoardVO(int seq,String name, String title, String content, String orgfilename, String savefilename) {
	  super();
	  	this.seq = seq;
	  	this.name = name;
	  	this.title = title;
		this.content = content;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
  }
		
		
		
public int getSeq() {
		return seq;
	}

  public void setSeq(int seq) {
		this.seq = seq;
	}
	
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getPass()
  {
    return this.pass;
  }
  
  public void setPass(String pass)
  {
    this.pass = pass;
  }
  
  public int getHit()
  {
    return this.hit;
  }
  
  public void setHit(int hit)
  {
    this.hit = hit;
  }
  
  public Date getRegdate()
  {
    return this.regdate;
  }
  
  public void setRegdate(Date regdate)
  {
    this.regdate = regdate;
  }

	public String getOrgfilename() {
		return orgfilename;
	}
	
	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}
	
	public String getSavefilename() {
		return savefilename;
	}
	
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public int getRef() {
		return ref;
	}
	
	public void setRef(int ref) {
		this.ref = ref;
	}
	
	public int getLev() {
		return lev;
	}
	
	public void setLev(int lev) {
		this.lev = lev;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}

}
