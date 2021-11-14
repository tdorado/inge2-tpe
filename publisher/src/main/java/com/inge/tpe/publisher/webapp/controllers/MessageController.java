package com.inge.tpe.publisher.webapp.controllers;

import com.inge.tpe.publisher.amqp.MessageSender;
import com.inge.tpe.publisher.models.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class MessageController {

    private final MessageSender messageSender;

    public MessageController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @GetMapping
    public String viewHome(Model model) {
        model.addAttribute("message", new Message());
        return "home";
    }

    @PostMapping("/sendMessage")
    public RedirectView sendMessage(@ModelAttribute("message") Message message, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/", true);
        Message sentMessage = messageSender.send(message);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        redirectAttributes.addFlashAttribute("sentMessage", sentMessage);
        return redirectView;
    }
}