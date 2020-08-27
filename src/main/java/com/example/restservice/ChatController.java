package com.example.restservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    @RequestMapping("/letsmeet")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null || username.isEmpty()) {
            return "redirect:/loginChat";
        }
        model.addAttribute("username", username);

        return "chat";
    }

    @RequestMapping(path = "/loginChat", method = RequestMethod.GET)
    public String showLoginChatPage() {
        return "loginChat";
    }

    @RequestMapping(path = "/loginChat", method = RequestMethod.POST)
    public String dologinChat(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "loginChat";
        }
        request.getSession().setAttribute("username", username);

        return "redirect:/letsmeet";
    }

    @RequestMapping(path = "/logoutChat")
    public String logoutChat(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/letsmeet";
    }
}
