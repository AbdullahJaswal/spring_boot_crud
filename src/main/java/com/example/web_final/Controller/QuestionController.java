package com.example.web_final.Controller;

import com.example.web_final.Entity.QuestionEntity;
import com.example.web_final.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class QuestionController
{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }


    @RequestMapping("/questionsHome")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("questionsHome");

        QuestionEntity questionEntity = new QuestionEntity();

        mv.addObject(questionEntity);

        return mv;
    }

    @ModelAttribute("question")
    public List<QuestionEntity> getAllQuestions()
    {
        return (List<QuestionEntity>) questionRepository.findAll();
    }

    @RequestMapping("/getQuestion")
    public ModelAndView getQuestion(@RequestParam int questionId)
    {
        ModelAndView mv = new ModelAndView("showQuestion");

        QuestionEntity questionEntity = questionRepository.findById(questionId).orElse(new QuestionEntity());

        mv.addObject("ques", questionEntity);

        return mv;
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public RedirectView addQuestion(QuestionEntity questionEntity)
    {
        questionRepository.save(questionEntity);

        ModelAndView mv = new ModelAndView("questionsHome");

        mv.addObject(questionEntity);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/questionsHome");
        return redirectView;
    }

    @RequestMapping(value = "/deleteQuestion")
    public RedirectView deleteQuestion(@RequestParam int questionId)
    {
        questionRepository.deleteById(questionId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/questionsHome");
        return redirectView;
    }
}
