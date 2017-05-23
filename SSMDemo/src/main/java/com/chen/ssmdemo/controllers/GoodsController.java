/**
 * 
 */
package com.chen.ssmdemo.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chen.ssmdemo.entities.Goods;
import com.chen.ssmdemo.services.GoodsService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author chenti
 *
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Resource
	GoodsService goodsService;
	
	/**
	 * 产品列表与分页Action
	 * */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required=false,defaultValue="1")int pageNO){
		int size = 5;
		model.addAttribute("size", size);
		model.addAttribute("pageNO", pageNO);
		model.addAttribute("count", goodsService.getGoodsCount());
		model.addAttribute("goods", goodsService.getGoodsPager(pageNO, size));
	    System.out.println("run here-----------------------------");
		return "/goods/list";
	}
	
	/**
	 * 删除单个产品对象Action
	 * pageNO是请求参数，保持状态的目的是为了删除后让页面继续停留在某一页；redirectAttributes是为了保持重定向后的message值。
	 * */
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id, @RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {
		if(goodsService.delete(id) > 0){
			redirectAttributes.addAttribute("message", "删除成功");
		}else{
			redirectAttributes.addAttribute("message", "删除失败");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	/**
	 * 删除多个产品对象Action
	*/
	@RequestMapping("/deletes")
	public String deletes(Model model, @RequestParam int[] ids, @RequestParam(required=false,defaultValue="1") int pageNO, RedirectAttributes redirectAttributes) {
		int rows = goodsService.deletes(ids);
		if(rows > 0){
			redirectAttributes.addAttribute("message", "删除"+rows+"行记录成功");
		}else {
			redirectAttributes.addAttribute("message", "删除失败");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	/**
	 * 添加商品
	 * */
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("good", new Goods());
		return "goods/add";
	}
	
	/**
	 * 添加商品保存
	 * 使用了JSR303校验，当保存对象是需要在参数前注解@ModelAttribute("good") @Valid,用于激活校验，否则页面将不会显示错误
	 * */
	@RequestMapping("/addSave")
	public String addSave(Model model, @ModelAttribute("good") @Valid Goods good, BindingResult bindingResult){
		//如果模型中存在错误
		if(!bindingResult.hasErrors()){
			if(goodsService.insert(good) > 0){
				return "redirect:/goods/list";
			}
		}
		model.addAttribute("good", good);
		return "goods/add";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
