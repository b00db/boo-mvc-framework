package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	  
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
	    if (member.getEmail() == null) { 	// 수정폼을 요청할 떄
	      Integer no = (Integer)model.get("no");
	      Member detailInfo = memberDao.selectOne(no);
	      model.put("member", detailInfo);
	      return "/member/MemberUpdateForm.jsp";

	    } else { 	// 회원 수정을 요청할 때
	      memberDao.update(member);
	      return "redirect:list.do";
	    }
	}

}
