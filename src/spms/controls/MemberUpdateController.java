package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao)model.get("memberDao");
	    
	    if (model.get("member") == null) { 	// 수정폼을 요청할 떄
	      Integer no = (Integer)model.get("no");
	      Member member = memberDao.selectOne(no);
	      model.put("member", member);
	      return "/member/MemberUpdateForm.jsp";

	    } else { 	// 회원 수정을 요청할 때
	      Member member = (Member)model.get("member");
	      memberDao.update(member);
	      return "redirect:list.do";
	    }
	}

}