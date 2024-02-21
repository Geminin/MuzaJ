package jakarta.faces.simplesecurity;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import muzyka.entities.Album;
import muzyka.list.AlbumList;

public class LazyCustomerDataModel extends LazyDataModel<Album> {

	private static final long serialVersionUID = 1L;


	AlbumList albumList;
	
	 @Override
	public Album getRowData(String rowKey) {
		
		for(Album album : albumList.getAlbumDao().findAlbumsByPerformer(getPageSize(), getPageSize(), rowKey)) {
			if(album.getAlbumId() == Integer.parseInt(rowKey)) {
				return album;
			}
		}
		
		return null;
		
	}
	
	 @Override
	 public String getRowKey(Album album) {
		 return String.valueOf(album.getAlbumId());
	 }
	


	public LazyCustomerDataModel(AlbumList albumList) {
		this.albumList = albumList;
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		
		Long count = albumList.getAlbumDao().AlbumCount();
		return count.intValue();
	}

	@Override
	public List<Album> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filters) {
		// apply offset & filters
		String titleFilter = albumList.getTitle();
		List<Album> album = albumList.getAlbumDao().findAlbumsByPerformer(pageSize, offset,titleFilter);

		return album;
	}

}
