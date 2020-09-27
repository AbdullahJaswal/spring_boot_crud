package com.example.web_final.Controller;

import com.example.web_final.Entity.PaperEntity;
import com.example.web_final.Repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class PaperController
{

    private final PaperRepository paperRepository;

    @Autowired
    public PaperController(PaperRepository paperRepository)
    {
        this.paperRepository = paperRepository;
    }


    @RequestMapping("/papersHome")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("papersHome");

        PaperEntity paperEntity = new PaperEntity();

        mv.addObject(paperEntity);

        return mv;
    }

    @ModelAttribute("paper")
    public List<PaperEntity> getAllPapers()
    {
        return (List<PaperEntity>) paperRepository.findAll();
    }

    @RequestMapping("/getPaper")
    public ModelAndView getPaper(@RequestParam int paperId)
    {
        ModelAndView mv = new ModelAndView("showPaper");

        PaperEntity paperEntity = paperRepository.findById(paperId).orElse(new PaperEntity());

        mv.addObject("paper", paperEntity);

        return mv;
    }

    @RequestMapping(value = "/addPaper", method = RequestMethod.POST)
    public RedirectView addPaper(PaperEntity paperEntity)
    {
        paperRepository.save(paperEntity);

        ModelAndView mv = new ModelAndView("papersHome");

        mv.addObject(paperEntity);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/papersHome");
        return redirectView;
    }

    @RequestMapping(value = "/deletePaper")
    public RedirectView deletePaper(@RequestParam int paperId)
    {
        paperRepository.deleteById(paperId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/papersHome");
        return redirectView;
    }
}
