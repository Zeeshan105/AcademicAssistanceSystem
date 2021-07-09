package comp3350.ASS.tests.persistance;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comp3350.AAS.application.Main;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.DataAccessObject;

public class DataAccessTest extends TestCase {
    private DataAccess dataAccess;
    private ArrayList<CardFolder> folderList;
    private ArrayList<Quiz> quizList;

    public DataAccessTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
//        System.out.println("\nStarting Persistence test DataAccess (using stub)");
//        dataAccess = new DataAccessStub();
//        dataAccess.open("Stub");
        System.out.println("\nStarting Persistence test DataAccess (using HSQLDB)");
        dataAccess = new DataAccessObject(Main.dbName);
        dataAccess.open(Main.getDbPathName());

        folderList = dataAccess.getFolders();
        quizList = dataAccess.getQuizList();
    }

    public void tearDown() {
        dataAccess.close();
//        System.out.println("Finished Persistence test DataAccess (using stub)");
        System.out.println("Finished Persistence test DataAccess (using HSQLDB)");
    }


    public void testFirstFolder(){
        ArrayList<FlashCard> flashCards = folderList.get(0).getCardList();
        assertEquals("Geographic Knowledge", folderList.get(0).getFolderName());

        FlashCard flashCard;

        flashCard=flashCards.get(0);
        assertEquals("National Land Area Descending Order", flashCard.getTitle());
        assertEquals("Russia>Canada>China>America>Brazil...", flashCard.getDescription());

        flashCard=flashCards.get(1);
        assertEquals("Temperature Distributed", flashCard.getTitle());
        assertEquals("Decreasing from low latitude to high latitude", flashCard.getDescription());

        System.out.println("\tPASS test 1st folder!");
    }

    public void testSecondFolder(){
        ArrayList<FlashCard> flashCards = folderList.get(1).getCardList();
        assertEquals("Historical Event", folderList.get(1).getFolderName());

        FlashCard flashCard;

        flashCard=flashCards.get(0);
        assertEquals("First World War", flashCard.getTitle());
        assertEquals("The World War I break out in 1914", flashCard.getDescription());

        flashCard=flashCards.get(1);
        assertEquals("Second World War", flashCard.getTitle());
        assertEquals("The World War II break out in 1939", flashCard.getDescription());

        System.out.println("\tPASS test 2nd folder!");
    }

    public void testThirdFolder(){
        ArrayList<FlashCard> flashCards = folderList.get(2).getCardList();
        assertEquals("Math Practice", folderList.get(2).getFolderName());

        FlashCard flashCard;

        flashCard=flashCards.get(0);
        assertEquals("Addition and subtraction within 10", flashCard.getTitle());
        assertEquals("1+1=2, 2+2=4", flashCard.getDescription());

        flashCard=flashCards.get(1);
        assertEquals("Calculate square root", flashCard.getTitle());
        assertEquals("sqrt(1)=+-1, sqrt(16)=+-4, sqrt(36)=+-6, sqrt(-1)=undefined", flashCard.getDescription());

        System.out.println("\tPASS test 3rd folder!");
    }

    public void testDeleteFolder(){
        assertEquals("Geographic Knowledge", folderList.remove(0).getFolderName());
        assertEquals("Historical Event", folderList.remove(0).getFolderName());
        assertEquals("Math Practice", folderList.remove(0).getFolderName());

        System.out.println("\tPASS test delete folder!");
    }


    public void testQuizName(){
        ArrayList<String> quizNameList = dataAccess.getAllQuizName();

        assertEquals("Historical", quizNameList.get(0));
        assertEquals("Geographic", quizNameList.get(1));
        assertEquals("Math", quizNameList.get(2));

        System.out.println("\tPASS test quiz name!");
    }

    public void testFirstQuiz(){
        ArrayList<Question> questions = quizList.get(0).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("Number of Canadians participating in World War II", question.getQuestion());
        assertEquals("0.5 million", question.getOption1());
        assertEquals("1 million", question.getOption2());
        assertEquals("2 million", question.getOption3());
        assertEquals("1 million", question.getKey());

        question=questions.get(1);
        assertEquals("What year was the founding of Canada?", question.getQuestion());
        assertEquals("1867", question.getOption1());
        assertEquals("1887", question.getOption2());
        assertEquals("1787", question.getOption3());
        assertEquals("1867", question.getKey());

        question=questions.get(2);
        assertEquals("Which aircraft manufacture belong to Canada?", question.getQuestion());
        assertEquals("Airbus", question.getOption1());
        assertEquals("Boeing", question.getOption2());
        assertEquals("Bombardier", question.getOption3());
        assertEquals("Bombardier", question.getKey());

        System.out.println("\tPASS test 1st quiz!");
    }

    public void testSecondQuiz(){
        ArrayList<Question> questions = quizList.get(1).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("What is the area of Canada in square kilometers?", question.getQuestion());
        assertEquals("9.98 million", question.getOption1());
        assertEquals("9.60 million", question.getOption2());
        assertEquals("9.37 million", question.getOption3());
        assertEquals("9.98 million", question.getKey());

        question=questions.get(1);
        assertEquals("What is the capital city of Canada?", question.getQuestion());
        assertEquals("Vancouver", question.getOption1());
        assertEquals("Ottawa", question.getOption2());
        assertEquals("Toronto", question.getOption3());
        assertEquals("Ottawa", question.getKey());

        System.out.println("\tPASS test 2nd quiz!");
    }

    public void testThirdQuiz(){
        ArrayList<Question> questions = quizList.get(2).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("What is the positive result of square root of 36?", question.getQuestion());
        assertEquals("3", question.getOption1());
        assertEquals("6", question.getOption2());
        assertEquals("9", question.getOption3());
        assertEquals("6", question.getKey());

        question=questions.get(1);
        assertEquals("What is the result of 1+1?", question.getQuestion());
        assertEquals("0", question.getOption1());
        assertEquals("1", question.getOption2());
        assertEquals("2", question.getOption3());
        assertEquals("2", question.getKey());

        System.out.println("\tPASS test 3rd quiz!");
    }

    public void testCompletedQuiz(){
        //TODO
        Quiz quiz = quizList.get(0);
        quiz.setCompleteStatus(true);

    }
    public void testAddQuiz() {
       //TODO
    }

}
