package demo.spring.boot.demospringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

@Slf4j
@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;


    /**
     * 发送简单的邮件
     *
     * @param prefix  邮箱的前缀,用来显示自定义中文
     * @param to
     * @param title
     * @param content
     */
    public boolean sendSimpleMail(String prefix, String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(prefix + "<" + from + ">");
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        log.info("邮件发送成功");
        return true;
    }

    /**
     * 发送简单的邮件(携带附件)
     *
     * @param to
     * @param title
     * @param content
     * @param fileList 文件列表
     */
    public boolean sendAttachmentsMail(String to, String title, String content, List<File> fileList) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file : fileList) {
                fileName = MimeUtility.encodeText(file.getName(), "GB2312", "B");
                helper.addAttachment(fileName, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        log.info("邮件发送成功");
        return true;
    }
}