package tw.jingxing.forum.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.jingxing.forum.model.dao.ForumsRepository;
import tw.jingxing.forum.model.entity.Forums;

@Service
@Transactional
public class ForumsService {

	@Autowired
	private ForumsRepository forumsRepository;

	/**
	 * 依傳入的ID查詢資料庫中的討論區.
	 * <p>
	 * 回傳討論區，若查無討論區則回傳null.
	 * </p>
	 * 
	 * @return Forums, if there exists. null, if there not exist.
	 */
	public Forums findForumById(Integer id) {
		Optional<Forums> op = forumsRepository.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	/**
	 * 依名字模糊查詢資料庫中的討論區.
	 * <p>
	 * 回傳討論區，若查無討論區則回傳null.
	 * </p>
	 * 
	 * @return Forums, if there exists. null, if there not exist.
	 */
	public Page<Forums> findByForumNameContaining(String name,Integer PageNo,Integer PageSize,String SortField,String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}
		if (SortMethod.equals("desc")) {
			return forumsRepository.findByForumNameContaining(name,PageRequest.of(offset, PageSize,
					Sort.by(SortField).descending()));
		} else {
			return forumsRepository.findByForumNameContaining(name,PageRequest.of(offset, PageSize,
					Sort.by(SortField).ascending()));
		}
		
		
		
	}

	/**
	 * 查詢資料庫中所有的討論區.
	 * <p>
	 * 回傳討論區的動態陣列.
	 * </p>
	 * 
	 * @return List of Forums
	 */
	public Page<Forums> findAllForums(Integer PageNo,Integer PageSize,String SortField,String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;
		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortMethod.equals("desc")) {
			return forumsRepository.findAll(PageRequest.of(offset,PageSize, Sort.by(SortField).descending()));
		} else {
			return forumsRepository.findAll(PageRequest.of(offset,PageSize, Sort.by(SortField).ascending()));
		}

	}

	/**
	 * 查詢資料庫中所有的討論區.
	 * <p>
	 * 回傳討論區的動態陣列.
	 * </p>
	 * 
	 * @return List of Forums
	 */
	public Page<Forums> findAllForumsBack(Integer PageNo,Integer PageSize,String SortField,String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}
		if (SortMethod.equals("desc")) {
			return forumsRepository.findAll(PageRequest.of(offset,PageSize,
					Sort.by(SortField).descending()));
		} else {
			return forumsRepository.findAll(PageRequest.of(offset, PageSize,
					Sort.by(SortField).ascending()));
		}

	}
	
	/**
	 * 查詢資料庫中所有的討論區.
	 * <p>
	 * 回傳討論區的動態陣列.
	 * </p>
	 * 
	 * @return Page of Forums
	 */
	public Page<Object[]> findAllForumsWithPop(Integer PageNo,Integer PageSize,String SortField,String SortMethod) {

		int offset = GlobalForumService.PageNo - 1;

		if (PageNo != null) {
			offset = PageNo - 1;
		}

		if (SortMethod.equals("desc")) {

			return forumsRepository.findAllBySortingWithPopular(
					PageRequest.of(offset,PageSize, Sort.by(SortField).descending()));
		} else {
			return forumsRepository.findAllBySortingWithPopular(
					PageRequest.of(offset, PageSize, Sort.by(SortField).ascending()));
		}

	}

	/**
	 * 依傳入的討論區資料新增討論區至資料庫中.
	 * <p>
	 * 回傳新增後的討論區.
	 * </p>
	 * 
	 * @return Forums
	 */
	public Forums insertForum(Forums forums) {
		return forumsRepository.save(forums);
	}

	/**
	 * 依傳入的討論區資料更新資料庫中的討論區.
	 * <p>
	 * 回傳更新後的討論區.
	 * </p>
	 * 
	 * @return Forums
	 */
	public Forums updateForum(Forums forums) {
		return forumsRepository.save(forums);
	}

	/**
	 * 以傳入的討論區ID刪除該討論區.
	 * <p>
	 * 刪除成功回傳true，若刪除失敗則回傳false.
	 * </p>
	 * 
	 * @return boolean
	 */
	public boolean deleteForumById(Integer id) {
		forumsRepository.deleteById(id);
		Optional<Forums> op = forumsRepository.findById(id);
		if (op.isEmpty()) {
			return true;
		}
		return false;
	}

	public Forums findByArticleId(Integer articleId) {
		return forumsRepository.findByArticleId(articleId);

	}

	
	public List<Forums> findAll() {
		return forumsRepository.findAll();
		
	}
	
}
