package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;



    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("users", new User());

        return "homepage";
    }
    @RequestMapping("/album")
    public String albumList(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        return "album";
    }
    @RequestMapping("/admin")
    public String adminList(Model model){
        model.addAttribute("albums", albumRepository.findAll());
        return "admin";
    }

    @GetMapping("/homepage")
    public String userForm(Model model) {
        model.addAttribute("users", new User());

        return "homepage";

    }

    @GetMapping("/transaction")
    public String transaction(Model model){
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("user", userRepository.findAll());
        return "transaction";
    }

//    @GetMapping("/album")
//    public String album(Model model){
//        model.addAttribute("album", new Album());
//        model.addAttribute("transaction", transactionRepository.findAll());
//        model.addAttribute("user", userRepository.findAll());
//        return "album";


    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("album", albumRepository.findAll());
        return "admin";
    }
    @GetMapping("/song")
    public String song(Model model){
        model.addAttribute("song", songRepository.findAll());
        return "song";

    }
    @GetMapping("/updatealbum")
    public String albumForm(Model model){
        model.addAttribute("album", albumRepository.findAll());
        return "updatealbum";
    }
    @PostMapping("/displayalbum")
    public String displayAlbum(Model model){
        model.addAttribute("album", albumRepository.findAll());
        return "updatealbum";
    }

    @PostMapping("/processuser")
    public String processUser(@Valid User user,
                              BindingResult result){
        if(result.hasErrors()){
            return "homepage";
        }
        userRepository.save(user);
        return "redirect:/album";
    }

    @PostMapping("/album")
    public String processAlbum(@Valid Album album,
                               BindingResult result){
        if(result.hasErrors()){
            return "album";
        }
        albumRepository.save(album);
        return "redirect:";
    }

    @PostMapping("/transaction")
    public String processTransaction(@Valid Transaction transaction,
                                     BindingResult result){
        if(result.hasErrors()){
            return "transaction";
        }
        transactionRepository.save(transaction);
        return "redirect:/";
    }
    @RequestMapping("/detailalbum/{id}")
    public String showAlbum(@PathVariable("id") long id, Model model){
        model.addAttribute("album", albumRepository.findById(id));
        return "album";
    }
    @RequestMapping("/updatealbum/{id}")
    public String updateAlbum(@PathVariable("id") long id, Model model){
        model.addAttribute("album", albumRepository.findById(id));
        return "updatealbum";
    }
    @RequestMapping("/deletealbum/{id}")
    public String delAlbum(@PathVariable("id") long id) {
        albumRepository.deleteById(id);
        return "redirect:/";
    }





}
