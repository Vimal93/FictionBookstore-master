package model;

import java.io.Serializable;

public class Review implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String comment;
	private String reviewer;
	private int likedislike; 

	public Review() {
		this.comment = "";
		this.reviewer = "";
		this.likedislike = -1;
	}
	
	public Review(String comment, String reviewer, int likedislike){
		this.comment = comment;
		this.reviewer = reviewer;
		this.likedislike = likedislike;
	}
	
	
	
	public int getLikedislike()
	{
		return likedislike;
	}
	
	public void setlikedislike(int likedislike)
	{
		this.likedislike=likedislike;
	}	
	
	public String getComment()
	{
		return comment;
	}
	
	public void setComment(String comment)
	{
		this.comment=comment;
	}
	
	public String getReviewer()
	{
		return reviewer;
	}
	
	public void setReviewer(String reviewer)
	{
		this.reviewer=reviewer;
	}

}
