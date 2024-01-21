package com.jsfcourse.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import muzyka.dao.AlbumDao;
import muzyka.entities.Album;

@Named
@RequestScoped
public class AlbumList {
	private static final String PAGE_Album_EDIT = "albumEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	AlbumDao albumDao;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Album> getFullList(){
		return albumDao.findAlbumsByPerformer(0);
	}
	public List<Album> getList(){
		List<Album> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		//2. Get list
		list = albumDao.getList(searchParams);
		
		return list;
	}

	public String newAlbum(){
		Album album = new Album();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("album", album);
		
		return PAGE_Album_EDIT;
	}

	public String editAlbum(Album album){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("album", album);
		
		return PAGE_Album_EDIT;
	}

	public String deleteAlbum(Album album){
		albumDao.remove(album);
		return PAGE_STAY_AT_THE_SAME;
	}


}
