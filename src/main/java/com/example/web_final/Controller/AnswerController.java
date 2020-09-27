package com.example.web_final.Controller;

import com.example.web_final.Entity.AnswerEntity;
import com.example.web_final.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class AnswerController
{

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerController(AnswerRepository answerRepository)
    {
        this.answerRepository = answerRepository;
    }


    @RequestMapping("/answersHome")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("answersHome");

        AnswerEntity answerEntity = new AnswerEntity();

        mv.addObject(answerEntity);

        return mv;
    }

    @ModelAttribute("answer")
    public List<AnswerEntity> getAllAnswers()
    {
        return (List<AnswerEntity>) answerRepository.findAll();
    }

    @RequestMapping("/getAnswer")
    public ModelAndView getAnswer(@RequestParam int answerId)
    {
        ModelAndView mv = new ModelAndView("showAnswer");

        AnswerEntity answerEntity = answerRepository.findById(answerId).orElse(new AnswerEntity());

        mv.addObject("ans", answerEntity);

        return mv;
    }

    @RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
    public RedirectView addAnswer(AnswerEntity answerEntity)
    {
        answerRepository.save(answerEntity);

        ModelAndView mv = new ModelAndView("answersHome");

        mv.addObject(answerEntity);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/answersHome");
        return redirectView;
    }

    @RequestMapping(value = "/deleteAnswer")
    public RedirectView deleteAnswer(@RequestParam int answerId)
    {
        answerRepository.deleteById(answerId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/answersHome");
        return redirectView;
    }
}
