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
 * 新闻列表
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
		//设置编码格式与MIME类型
		System.out.println("servlet-----------------------------------------");
		response.setContentType("text/html;charset=UTF-8");
		String basePath = "D:/Ti/Eclipse-Workspace/FreeMarkerDemo2/src/main/webapp/news/";
		//首页新闻列表路径
		String indexPath = basePath+"index.html";
		
		//文件是否存在
		File file = new File(indexPath);
		if(!file.exists()){
			System.out.println("file not exist-----------------------------------------");
			//如果新闻列表不存在，生成新闻列表
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
			
			//合并模板和数据模型
			try {
				//将数据与模板渲染的结果写入文件
				Writer writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
				template.process(articleData, writer);
				writer.flush();
				
				articleData.clear();
				template = cfg.getTemplate("news.ftl");
				
				//生成单个新闻文件
				for (Article article : articles) {
					articleData.put("article", article);
					//单个新闻文件
					file = new File(basePath+article.getId()+".html");
					if(!file.exists()){
						file.createNewFile();
						System.out.println("subfile create success-----------------------------------------");
					}
					//文件输出流写入器
					writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
					
					//将模板和数据生成的结果写入文件中，得到一个静态文件
					template.process(articleData, writer);
					writer.flush();
				}
				
				writer.close();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//如果新闻单页下存在，生成新闻单页
		request.getRequestDispatcher("/news/index.html").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	
}
