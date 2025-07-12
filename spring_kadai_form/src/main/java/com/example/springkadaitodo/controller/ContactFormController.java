package com.example.springkadaitodo.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaitodo.form.ContactForm;

@Controller
public class ContactFormController {
	@GetMapping("/form")
	public String form(Model model) {
		// すでにインスタンスが存在する場合は行わない
		if (!model.containsAttribute("contactForm")) {
			// ビューにフォームクラスのインスタンスを渡す
			model.addAttribute("contactForm", new ContactForm());
		}
		return "contactFormView";
	}

	@PostMapping("/confirm")
	public String confirm(RedirectAttributes redirectAttributes, Model model,
			@Validated ContactForm form, BindingResult result) {
        // バリデーションエラーがあったら終了
        if (result.hasErrors()) {
            // フォームクラスをビューに受け渡す
            redirectAttributes.addFlashAttribute("contactForm", form);
            // バリデーション結果をビューに受け渡す
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
                    + Conventions.getVariableName(form), result);

            // formにリダイレクトしてエラーを表示
            return "redirect:/form";
        }
        //バリデーションエラーがない場合フォームクラスをビューへ受け渡す
		model.addAttribute("contactForm", form);
		return "confirmView";
	}
}
