package com.chen.freemarkerdemo.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.freemarkerdemo.entities.Article;
import com.chen.freemarkerdemo.services.ArticleService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * �����б�
 * @author tichen
 *
 */
@WebServlet("/News")
public class News extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArticleService articleService = new ArticleService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���ñ����ʽ��MIME����
		System.out.println("servlet-----------------------------------------");
		response.setContentType("text/html;charset=UTF-8");
		String basePath = "D:/Ti/Eclipse-Workspace/FreeMarkerDemo2/src/main/webapp/news/";
		//��ҳ�����б�·��
		String indexPath = basePath+"index.html";
		
		//�ļ��Ƿ����
		File file = new File(indexPath);
		if(!file.exists()){
			System.out.println("file not exist-----------------------------------------");
			//��������б����ڣ����������б�
			file.createNewFile();
			System.out.println("file create success-----------------------------------------");
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
			String templatePath = this.getClass().getClassLoader().getResource("/templates").getPath();
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			cfg.setDefaultEncoding("UTF-8");
			
			Map<String, Object> articleData = new HashMap<String, Object>();
			List<Article> articles = articleService.getArticles();
			articleData.put("articles", articles);
			
			Template template = cfg.getTemplate("newsList.ftl");
			
			//�ϲ�ģ�������ģ��
			try {
				//��������ģ����Ⱦ�Ľ��д���ļ�
				Writer writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
				template.process(articleData, writer);
				writer.flush();
				
				articleData.clear();
				template = cfg.getTemplate("news.ftl");
				
				//���ɵ��������ļ�
				for (Article article : articles) {
					articleData.put("article", article);
					//���������ļ�
					file = new File(basePath+article.getId()+".html");
					if(!file.exists()){
						file.createNewFile();
						System.out.println("subfile create success-----------------------------------------");
					}
					//�ļ������д����
					writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
					
					//��ģ����������ɵĽ��д���ļ��У��õ�һ����̬�ļ�
					template.process(articleData, writer);
					writer.flush();
				}
				
				writer.close();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//������ŵ�ҳ�´��ڣ��������ŵ�ҳ
		request.getRequestDispatcher("/news/index.html").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	
}
