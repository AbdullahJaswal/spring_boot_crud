package com.example.web_final.Controller;

import java.util.List;

import com.example.web_final.Entity.SubjectEntity;
import com.example.web_final.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SubjectController
{

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }


    @RequestMapping("/subjectsHome")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("subjectsHome");

        SubjectEntity subjectEntity = new SubjectEntity();

        mv.addObject(subjectEntity);

        return mv;
    }

    @ModelAttribute("subject")
    public List<SubjectEntity> getAllSubjects()
    {
        return (List<SubjectEntity>) subjectRepository.findAll();
    }

    @RequestMapping("/getSubject")
    public ModelAndView getSubject(@RequestParam int subjectId)
    {
        ModelAndView mv = new ModelAndView("showSubject");

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(new SubjectEntity());

        mv.addObject("sub", subjectEntity);

        return mv;
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public RedirectView addSubject(SubjectEntity subjectEntity)
    {
        subjectRepository.save(subjectEntity);

        ModelAndView mv = new ModelAndView("subjectsHome");

        mv.addObject(subjectEntity);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/subjectsHome");
        return redirectView;
    }

    @RequestMapping(value = "/deleteSubject")
    public RedirectView deleteSubject(@RequestParam int subjectId)
    {
        subjectRepository.deleteById(subjectId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/subjectsHome");
        return redirectView;
    }
}
