package comtrue.homework.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MailBean extends QuartzJobBean{
	
	private MailService MailService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		MailService.timemail();
	}

	public void setMailService(MailService MailService) {
		this.MailService = MailService;
	}
	
	
}
