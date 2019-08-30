/**
 * ou are to write an application that is kind of like twitter. It will allow a user to
 post a message, and everyone else to see all the messages that have been posted.

 It will not allow for following, retweets, direct messages or profiles. It is a
 very basic application, but one that proves what you have learned.

 Build an application that allows you to add MESSAGEs, list them and view them (like Lesson 10)

 MESSAGEs should look like this:

 MESSAGE
 long id
 String content
 String posteddate (If you'd like, you can make this data type 'Date')
 String sentby

 the home page ("/") path should point to a list of all MESSAGES

 every page (or template) should have link (or button) to the add path
 ("/add") which is the new MESSAGE form
 */
package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller

public class HomeController {
    @Autowired
    MessageRepository messageRepository;


    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("message", messageRepository.findAll());
        return "message";
    }

    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }


    @PostMapping("/process")
    public String procressForm(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("message", messageRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "messageform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id) {
        messageRepository.deleteById(id);
        return "redirect:/";
    }
}

/*
public class HomeController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String messageMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "message";
    }
    @GetMapping("/add")
    public String newMessage(Model model){
        model.addAttribute("message", new Message());
        return  "messageform";
    }
    @PostMapping("/add")
    public String processMessage(@ModelAttribute Message message,
                               @RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return  "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            message.setFile(uploadResult.get("url").toString());
            messageRepository.save(message);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return  "redirect:/";
    }
    @PostMapping("/process")
    public String procressForm(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showmessage(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("message", messageRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "messageform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id) {
        messageRepository.deleteById(id);
        return "redirect:/";
    }
}
*/