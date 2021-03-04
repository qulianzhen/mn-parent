package com.mn.util;

import com.mn.mnutil.ThreadPoolUtil;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * java mail 发送邮件
 * @author qulianzhen
 */

@Component
public class SpringMailUtil {
	
    private static final Logger LOG = LoggerFactory.getLogger(SpringMailUtil.class);

	private static JavaMailSender mailSender;

	private static String from;

	//发送邮件的模板引擎
	private static FreeMarkerConfigurer templateConfigurer;



	/**
     * 发送简单邮件的方法
     * @param to 收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public static void sendMailSimple(String to, String subject, String content){
    	//这里只是捕捉了创建线程的异常，但是不能处理到子线程内的异常，没有必要，子线程只记录日志即可。
    	try {
    		ThreadPoolUtil.getInstance().execute(()->{
    			//子线程自己捕捉异常，记录日志
    			try {
	    			SimpleMailMessage simpleMessage = new SimpleMailMessage();
	    			simpleMessage.setFrom(from);
	    			simpleMessage.setTo(to);
	    			simpleMessage.setSubject(subject);
	    			//邮件的正文，第二个boolean类型的参数代表html格式
	    			simpleMessage.setText(content);
	    			//发送
	    			mailSender.send(simpleMessage);
    			}catch (Exception e) {
    	        	LOG.error("exec send mail error!", e);
    	        }
    		});
        } catch (Exception e) {
        	LOG.error("ThreadPoolUtil exec send mail Thread error!", e);
        }
    	
    }


	/**
	 * 发送模板邮件
	 * @param params 模板数据集
	 * @param subject 邮件主题
	 * @param templateName 模板路径
	 * @param to 发送给
	 * @param receipt 是否需要回执
	 * @throws Exception
	 */
	public static void sendTempMessageMail(Object params, String subject, String templateName, String to, boolean receipt) throws Exception{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		if(receipt) {
			mimeMessage.setHeader("Disposition-Notification-To", "1");
		}
		mimeMessage.setContentID(System.currentTimeMillis()+"");
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(InternetAddress.parse(to));
		helper.setSubject("【" + subject + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");//邮件标题
		Map<String, Object> model = new HashMap<>();
		model.put("params", params);
		Template template = templateConfigurer.getConfiguration().getTemplate(templateName);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		helper.setText(text, true);
		System.out.println(mimeMessage.getContentID());
		mailSender.send(mimeMessage);
	}



	public static void readMail(){
		// 准备连接服务器的会话信息
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", "imap.163.com");
		props.setProperty("mail.imap.port", "143");

		Store store = null;
		Folder folder = null;

		try{
			// 创建Session实例对象
			Session session = Session.getInstance(props);

			// 创建IMAP协议的Store对象
			store = session.getStore("imap");

			// 连接邮件服务器
			store.connect("jxcfsyj163@163.com", "DKNGEGVOUWCSVXWC");

			// 获得收件箱
			folder = store.getFolder("INBOX");
			// 以读写模式打开收件箱
			folder.open(Folder.READ_WRITE);

			// 获得收件箱的邮件列表
			Message[] messages = folder.getMessages();

			// 打印不同状态的邮件数量
			System.out.println("收件箱中共" + messages.length + "封邮件!");
			System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
			System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
			System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

			System.out.println("------------------------开始解析邮件----------------------------------");
		}catch (Exception e){
			LOG.error("read mail error", e);
		}finally{
			// 关闭资源
			if(folder!=null && folder.isOpen()){
				try {
					folder.close(false);
				} catch (MessagingException e) {
					LOG.error("close mail error", e);
				}
			}
			if(store!=null && store.isConnected()){
				try {
					store.close();
				} catch (MessagingException e) {
					LOG.error("close mail error", e);
				}
			}
		}
	}

    public static void readMailPop(){
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "pop3");
        props.setProperty("mail.smtp.host", "pop.163.com");
        props.setProperty("mail.pop.port", "110");

        Store store = null;
        Folder folder = null;

        try{
            // 创建Session实例对象
            Session session = Session.getDefaultInstance(props);

            // 创建IMAP协议的Store对象
            store = session.getStore("pop3");

            // 连接邮件服务器
            store.connect("pop.163.com","jxcfsyj163@163.com", "DKNGEGVOUWCSVXWC");

            // 获得收件箱
            folder = store.getFolder("INBOX");
            // 以读写模式打开收件箱
            folder.open(Folder.READ_WRITE);

            // 获得收件箱的邮件列表
            Message[] messages = folder.getMessages();

            // 打印不同状态的邮件数量
            System.out.println("收件箱中共" + messages.length + "封邮件!");
            System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
            System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
            System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

            System.out.println("------------------------开始解析邮件----------------------------------");
        }catch (Exception e){
            LOG.error("read mail error", e);
        }finally{
            // 关闭资源
            if(folder!=null && folder.isOpen()){
                try {
                    folder.close(false);
                } catch (MessagingException e) {
                    LOG.error("close mail error", e);
                }
            }
            if(store!=null && store.isConnected()){
                try {
                    store.close();
                } catch (MessagingException e) {
                    LOG.error("close mail error", e);
                }
            }
        }
    }

    public static void readMailSSL(){
        //设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.imap.host", "imap.partner.outlook.cn");
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.socketFactory.port", "993");
        props.setProperty("mail.imap.auth", "true");
        //建立邮件会话
        Session session = Session.getInstance(props, null);
        //设置连接邮件仓库的环境
        URLName url = new URLName("imap", "imap.partner.outlook.cn", 993, null,
                "jxcfsyj163@163.com", "DKNGEGVOUWCSVXWC");
        Store store = null;
        Folder inbox = null;
        try {
            //得到邮件仓库并连接
            store = session.getStore(url);
            store.connect();
            //得到收件箱并抓取邮件
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            //false代表未读，true代表已读
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            //根据设置好的条件获取message
            Message[] messages = inbox.search(ft);
            //Message[] messages = inbox.getMessages();
            inbox.fetch(messages, profile);
            int length = messages.length;
            System.out.println("收件箱的邮件数：" + length);
            System.out.println("-------------------------------------------");

            //遍历收件箱、垃圾箱等名称
            Folder defaultFolder = store.getDefaultFolder();
            Folder[] folders = defaultFolder.list();

            for (int i = 0; i < folders.length; i++) {
                System.out.println("名称："+folders[i].getName());
            }

            for (int i = 0; i < length; i++) {
                String from = MimeUtility.decodeText(messages[i].getFrom()[0].toString());
                InternetAddress ia = new InternetAddress(from);
                System.out.println("发件人：" + ia.getPersonal() + '(' + ia.getAddress() + ')');
                System.out.println("主题：" + messages[i].getSubject());
                System.out.println("邮件发送时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(messages[i].getSentDate()));
                System.out.println("-------------------------------------------\n");
                messages[i].setFlag(Flags.Flag.SEEN, true);
            }
        } catch (Exception e) {
            LOG.error("read mail error", e);
        } finally {
            try {
                if(inbox != null && inbox.isOpen()) {
                    inbox.close(false);
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                if(store != null && store.isConnected()) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }



	@Value("${spring.mail.username}")
	public void setFrom(String from) {
		SpringMailUtil.from = from;
	}

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		SpringMailUtil.mailSender = mailSender;
	}

	@Autowired
	public void setTemplateConfigurer(FreeMarkerConfigurer templateConfigurer) {
		SpringMailUtil.templateConfigurer = templateConfigurer;
	}
}
