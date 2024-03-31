package tw.jingxing.forum.model.service;

import org.springframework.stereotype.Service;

@Service
public class GlobalForumService {

	
	public static final int Article_Page_Size=5;
	public static final String Articles_Sort_Field="articleDate";
	public static final String Article_Sort_Method="desc";
	
	public static final int Back_Article_Page_Size=5;
	public static final String Back_Articles_Sort_Field="articleDate";
	public static final String Back_Article_Sort_Method="desc";
	
	public static final int Forum_Page_Size=5;
	public static final String Forums_Sort_Field="forumId";
	public static final String Forum_Sort_Method="desc";
	
	public static final int Back_Forum_Page_Size=10;
	public static final String Back_Forums_Sort_Field="forumId";
	public static final String Back_Forum_Sort_Method="desc";
	
	public static final int PageNo=1;
	
	
	
	
	
	
}
