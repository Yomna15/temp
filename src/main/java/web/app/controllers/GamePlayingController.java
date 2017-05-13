package web.app.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.app.entities.Game;
import web.app.entities.Question;
import web.app.services.GameService;
import web.app.services.QuestionService;
@Controller
public class GamePlayingController {
	public Game game;
	public Question question =new Question();
    ArrayList<Question> q ;
    ArrayList<Question> q1 = new ArrayList<Question>();


	private GameService gameservice;
	private QuestionService questionservice;
	int NumberOfAnswerdQuestions;
    @Autowired
	public void  SetGameService(GameService gs)
	{
		gameservice=gs;	
	}
    @Autowired
  	public void  setQuestionService(QuestionService qs)
  	{
  		questionservice=qs;	
  	}
    @RequestMapping(value="/game/find/{name}",method=RequestMethod.GET)
    public ModelAndView Showdescription(@PathVariable String name)
    {
    	game=new Game();
    	NumberOfAnswerdQuestions=0;
    	game=gameservice.FindGame(name);
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("gamedescription");
    	mv.addObject("game",game);
    	//System.out.println("i am hereeeee " + game.getQuestions().size());

    	return mv;
    	
    	
    }
    @RequestMapping(value="/game/ask/question",method=RequestMethod.GET)
    public ModelAndView ShowdQuestion()
    {
		//System.out.println("i am heree"+game.getName());

         q=questionservice.find(game.getName());
    	ModelAndView mv=new ModelAndView();
    	int numberofques = q.size();
    	if(NumberOfAnswerdQuestions < numberofques)
    	{
    		
    	    question=q.get(NumberOfAnswerdQuestions);
    	   // NumberOfAnswerdQuestions++;
    	    mv.setViewName("questionpage");
    	    mv.addObject("question",question);
    
    	}
    	else
    	{
    	//System.out.println("I am hereeee" + game.getQuestions().size());
    		game.setQuestions(q1);
    		for(int i=0;i<q1.size();i++)
    		{
    			System.out.println("userans is" + q1.get(i).getUserAnswer());
    		}
    		gameservice.CalculateScore(game);
    		mv.setViewName("scorepage");
    		mv.addObject("score",gameservice.CalculateScore(game));
  
    		
    	}
    	return mv;
    	
    }
    @RequestMapping(value="/game/correct/question",method=RequestMethod.GET)
    public ModelAndView ShowdQuestion(@ModelAttribute Question question)
    {
         String UserAnswer=question.getUserAnswer();
         q.get(NumberOfAnswerdQuestions).setUserAnswer(UserAnswer);
         question=q.get(NumberOfAnswerdQuestions);
        // q.set(NumberOfAnswerdQuestions, question);
         q1.add(question);
         
         NumberOfAnswerdQuestions++;
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("questionresult");
    	return mv;
    	
    }

}

