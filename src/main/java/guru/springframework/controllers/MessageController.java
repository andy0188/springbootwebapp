package guru.springframework.controllers;

import guru.springframework.domain.Message;
import guru.springframework.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("messages", messageService.listAllMessages());
        return "messages";
    }

    @RequestMapping("message/{id}")
    public String showMessage(@PathVariable Integer id, Model model){
        model.addAttribute("message", messageService.getMessageById(id));
        return "messageshow";
    }

    @RequestMapping("message/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("message", messageService.getMessageById(id));
        return "messageform";
    }

    @RequestMapping("message/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        messageService.delete(id);
        return "redirect:/messages";
    }

    @RequestMapping("message/new")
    public String newMessage(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }

    @RequestMapping(value = "message", method = RequestMethod.POST)
    public String saveMessage(Message message){

        messageService.saveMessage(message);

        return "redirect:/message/" + message.getId();
    }

}
