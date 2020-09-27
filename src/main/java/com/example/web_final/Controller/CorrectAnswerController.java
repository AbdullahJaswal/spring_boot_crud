package com.example.web_final.Controller;

import com.example.web_final.Entity.CorrectAnswerEntity;
import com.example.web_final.Repository.CorrectAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class CorrectAnswerController
{

    private final CorrectAnswerRepository correctAnswerRepository;

    @Autowired
    public CorrectAnswerController(CorrectAnswerRepository correctAnswerRepository)
    {
        this.correctAnswerRepository = correctAnswerRepository;
    }


    @RequestMapping("/correctAnswersHome")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("correctAnswersHome");

        CorrectAnswerEntity correctAnswerEntity = new CorrectAnswerEntity();

        mv.addObject(correctAnswerEntity);

        return mv;
    }

    @ModelAttribute("correctAnswer")
    public List<CorrectAnswerEntity> getAllCorrectAnswers()
    {
        return (List<CorrectAnswerEntity>) correctAnswerRepository.findAll();
    }

    @RequestMapping("/getCorrectAnswer")
    public ModelAndView getCorrectAnswer(@RequestParam int correctAnswerId)
    {
        ModelAndView mv = new ModelAndView("showCorrectAnswer");

        CorrectAnswerEntity correctAnswerEntity = correctAnswerRepository.findById(correctAnswerId).orElse(new CorrectAnswerEntity());

        mv.addObject("cAns", correctAnswerEntity);

        return mv;
    }

    @RequestMapping(value = "/addCorrectAnswer", method = RequestMethod.POST)
    public RedirectView addCorrectAnswer(CorrectAnswerEntity correctAnswerEntity)
    {
        correctAnswerRepository.save(correctAnswerEntity);

        ModelAndView mv = new ModelAndView("correctAnswersHome");

        mv.addObject(correctAnswerEntity);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/correctAnswersHome");
        return redirectView;
    }

    @RequestMapping(value = "/deleteCorrectAnswer")
    public RedirectView deleteCorrectAnswer(@RequestParam int correctAnswerId)
    {
        correctAnswerRepository.deleteById(correctAnswerId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/correctAnswersHome");
        return redirectView;
    }
}
