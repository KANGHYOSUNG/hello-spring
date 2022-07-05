package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}

	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		// 작성 중 command + shift + enter > 자동완성됨
		// (ex Hello hello = new Hello( 요시점에 단축키 ㄱ)
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}

	static class Hello { // static 클래스를 생성하면 상위 클래스안에서 그냥 사용할 수 있음.
		private String name;

		// getter, setter 단축키 : control + n or control + enter
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
