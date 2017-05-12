package com.chen.FreeMarkerDemo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, TemplateException
    {
    	//创建一个freemarker.template.Configuration实例，它是存储FreeMarker应用级设置的核心部分
    	//指定版本号
    	Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    	
    	//设置模板目录
    	cfg.setDirectoryForTemplateLoading(new File("src/main/java/templates"));
    	
    	//设置默认编码格式
    	cfg.setDefaultEncoding("UTF-8");
    	
    	//数据
    	Map<String, Object> product = new HashMap<String, Object>();
    	product.put("name", "iphone 7");
    	product.put("price", "5000");
    	product.put("users", new String[]{"Tom","Jack","Rose"});
    	
    	//从设置的目录中获得模板
    	Template template = cfg.getTemplate("product.ftl");
    	
    	//合并模板和数据模型
    	Writer out = new OutputStreamWriter(System.out);
    	template.process(product, out);
    	
    	//关闭
    	out.flush();
    	out.close();
    	
    	
    }
}
