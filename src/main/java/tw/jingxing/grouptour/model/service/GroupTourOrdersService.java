package tw.jingxing.grouptour.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import tw.jingxing.grouptour.model.bean.GroupTourOrdersBean;
import tw.jingxing.grouptour.model.dao.GroupTourOrdersRepository;

@Service
@Transactional
public class GroupTourOrdersService {
	
	@Autowired
	private GroupTourOrdersRepository groupTourOrdersRepository;
	
	@Autowired
    private JavaMailSender emailSender;
	
//	後台訂單細項新增資料
	public GroupTourOrdersBean Insert(GroupTourOrdersBean T ) throws IOException {
			return groupTourOrdersRepository.save(T);			
	}
	 
//	後台訂單細項修改資料(Id)
	public GroupTourOrdersBean updateById(GroupTourOrdersBean T) {
			return groupTourOrdersRepository.save(T);			
	}
	
//	後台訂單細項刪除資料(Id)
	public void  deleteById(Integer id) {
		Optional<GroupTourOrdersBean> groupTourOrdersId = groupTourOrdersRepository.findById(id);	
		if (groupTourOrdersId.isPresent()) {
			groupTourOrdersRepository.deleteById(id);	
		}else {
			throw new RuntimeException("此訂單id不存在，無法刪除");
		}
	}
	
	
// 後台查詢訂單id
	public GroupTourOrdersBean findById(Integer id) {
		Optional<GroupTourOrdersBean> ShoppingCartId = 
				groupTourOrdersRepository.findById(id);
		return ShoppingCartId.orElse(null);
	}
	
// 查詢全部
	public List<GroupTourOrdersBean> findAll(){
		return groupTourOrdersRepository.findAll();
	}
	
//	寄email方法
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // Set text to true if it is HTML
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

	
	
	
}
