/**
 * 
 */
package com.chen.ssmdemo.controllers;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chen.ssmdemo.entities.Goods;
import com.chen.ssmdemo.services.GoodsService;

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
	
	/**
	 * 编辑商品
	 * */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id){
		model.addAttribute("good", goodsService.getGoodsById(id));
		return "goods/edit";
	}
	
	/**
	 * 编辑保存
	 * TODO
	 * @param model
	 * @param good
	 * @param bindingResult  Spring 验证的错误返回
	 * @return
	 * String
	 */
	@RequestMapping("/editSave")
	public String editSave(Model model, @ModelAttribute("good") @Valid Goods good, BindingResult bindingResult){
		if(!bindingResult.hasErrors()){
			if(goodsService.update(good) > 0){
				return "redirect:list";
			}
		}
		//如果模型中存在错误
		model.addAttribute("good", good);
		return "/goods/edit";
	}
	
	/**
	 * 上传图片
	 * TODO
	 * @param model
	 * @param id
	 * @return
	 * String
	 */
	@RequestMapping("/upPicture/{id}")
	public String upPicture(Model model, @PathVariable int id){
		model.addAttribute("good", goodsService.getGoodsById(id));
		return "goods/upfile";
	}
	
	/**
	 * 上传图片保存
	 * TODO
	 * @param model
	 * @param id
	 * @return
	 * String
	 */
	@RequestMapping("/upPictureSave/{id}")
	public String upPictureSave(Model model, @PathVariable int id, MultipartFile picFile, HttpServletRequest request){
		Goods good = goodsService.getGoodsById(id);
		
		//如果选择了文件
		if(picFile != null){
			//如果文件大小不为0
			if(picFile.getSize() > 0){
				//活得上传位置
				String path = request.getServletContext().getRealPath("/images");
				System.out.println("path: "+path);
				//生成文件名
				String str = picFile.getOriginalFilename();
				System.out.println(str);
				String filename = UUID.randomUUID().toString()+picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				File tempFile = new File(path, filename);
				try {
					//保存文件
					picFile.transferTo(tempFile);
					//更新数据
					good.setPicture(filename);
					goodsService.update(good);
					
					return "redirect:/goods/list";
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		model.addAttribute("good", good);
		return "goods/upfile";
	}
	
	
	
	
	
	
	
	
	
	
}
