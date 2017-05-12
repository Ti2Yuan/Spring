package com.chen.freemarkerdemo.services;

import java.util.ArrayList;
import java.util.List;

import com.chen.freemarkerdemo.entities.Article;

/**
 * ����ҵ����
 * @author tichen
 *
 */
public class ArticleService {

	private static List<Article> articles;

    static {
        articles = new ArrayList<Article>();
        articles.add(new Article(20160701, "��������������˱�UFO������ ��ʵ�ǳ���7��","����������ɼ�ʱ��������������ʱ��������(����ʱ������)���������������������ݡ��ڻ����ݡ������������ݣ���ǧ�˱�����ҹ�յ����ػ����ŵ�"));
        articles.add(new Article(20160702, "��������ʥĸԺΪ���ÿ�Ϯ�������񸸾��д�����", "��������ս��˾�֤ʵ����ʵ�����й������ߺŻ�����½�������㣬�պþ������������Ǹ�����"));
        articles.add(new Article(20160703, "�ն���֪�º�ѡ��С�ذٺ��ӻػ�ʯԭ��Ũױ����", "Ȼ���������������ɲ������࣬��Щ������������Щ�����Ƿɻ����壬����Щ�˲²��������ꡣ"));
        articles.add(new Article(20160704, "����ο�����������׶����� ����ʾ���ߴ��뿹��","����ս��˾������˱�ʾ����ĿǰΪֹ��û���κ����𱨸棬��˵��������ͨ���ڴ����оͻ���ʧ����Ҳ������Ϊ�γ���һ������ۣ���һ�ж���δ���ʲô��в��"));
        articles.add(new Article(20160705, "���չ�ϵ����ʮ��·����ӦѰ������뻪��ͻ","�й������ߺŻ��6��25���ں����Ĳ����췢�������״η��䣬���ɹ����ս���������ѧ��ָ�������ߺŵڶ������һֱ�ڵ���͹����У�һ���º����½�������㡣"));
    }

    /**
     * ���е�����
     */
    public List<Article> getArticles() {
        return articles;
    }
    
    /*
     * �������ͨ�����±��
     */
    public Article getArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
