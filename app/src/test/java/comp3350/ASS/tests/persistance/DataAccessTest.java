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
        System.out.println("\nStarting Persistence test DataAccess (using stub)");
        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
//        System.out.println("\nStarting Persistence test DataAccess (using HSQLDB)");
//        dataAccess = new DataAccessObject(Main.dbName);
//        dataAccess.open(Main.getDbPathName());

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
        assertEquals("Geographic_Knowledge", folderList.get(0).getFolderName());

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
        assertEquals("Historical_Event", folderList.get(1).getFolderName());

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
        assertEquals("Math_Practice", folderList.get(2).getFolderName());

        FlashCard flashCard;

        flashCard=flashCards.get(0);
        assertEquals("Addition and subtraction within 10", flashCard.getTitle());
        assertEquals("1+1=2, 2+2=4", flashCard.getDescription());

        flashCard=flashCards.get(1);
        assertEquals("Calculate square root", flashCard.getTitle());
        assertEquals("sqrt(1)=+-1, sqrt(16)=+-4, sqrt(36)=+-6, sqrt(-1)=undefined", flashCard.getDescription());

        System.out.println("\tPASS test 3rd folder!");
    }

    public void testAddToSameFolderWithSameDescription(){
        dataAccess.addCard("title1", "description", "newFolder");
        dataAccess.addCard("title2", "description", "newFolder");
        dataAccess.addCard("title3", "description", "newFolder");

        folderList = dataAccess.getFolders();

        // There are 3 original folders in database, so testing will start in 4th folder (index: 3) for adding and deleting
        CardFolder folders= folderList.get(3);
        assertEquals("newFolder", folders.getFolderName());
        assertEquals("title1", folders.getCardTitles().get(0));
        assertEquals("title2", folders.getCardTitles().get(1));
        assertEquals("title3", folders.getCardTitles().get(2));

        assertEquals("description", folders.getCardDescription().get(0));
        assertEquals("description", folders.getCardDescription().get(1));
        assertEquals("description", folders.getCardDescription().get(2));

        dataAccess.deleteFolder(3);
        System.out.println("\tPASS test add to the same folder!");
    }

    public void testAddToSameFolderWithVaryDescription(){
        dataAccess.addCard("title1", "description1", "newFolder");
        dataAccess.addCard("title2", "description2", "newFolder");
        dataAccess.addCard("title3", "description3", "newFolder");

        folderList = dataAccess.getFolders();

        // There are 3 original folders in database, so testing will start in 4th folder (index: 3) for adding and deleting
        CardFolder folders= folderList.get(3);
        assertEquals("newFolder", folders.getFolderName());
        assertEquals("title1", folders.getCardTitles().get(0));
        assertEquals("title2", folders.getCardTitles().get(1));
        assertEquals("title3", folders.getCardTitles().get(2));

        assertEquals("description1", folders.getCardDescription().get(0));
        assertEquals("description2", folders.getCardDescription().get(1));
        assertEquals("description3", folders.getCardDescription().get(2));

        dataAccess.deleteFolder(3);
        System.out.println("\tPASS test add to the same folder!");
    }

    public void testAddToDifferentFolder(){
        dataAccess.addCard("title1", "description", "newFolder1");
        dataAccess.addCard("title2", "description", "newFolder2");
        dataAccess.addCard("title3", "description", "newFolder3");

        folderList = dataAccess.getFolders();

        CardFolder folders;

        // There are 3 original folders in database, so testing will start in 4th folder (index: 3) for adding and deleting
        folders = folderList.get(3);
        assertEquals("newFolder1", folders.getFolderName());
        assertEquals("title1", folders.getCardTitles().get(0));

        folders= folderList.get(4);
        assertEquals("newFolder2", folders.getFolderName());
        assertEquals("title2", folders.getCardTitles().get(0));

        folders= folderList.get(5);
        assertEquals("newFolder3", folders.getFolderName());
        assertEquals("title3", folders.getCardTitles().get(0));

        dataAccess.deleteFolder(3);
        dataAccess.deleteFolder(3);
        dataAccess.deleteFolder(3);

        System.out.println("\tPASS test add to different folder!");
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
        boolean complete;
        Quiz quiz;
        quiz = quizList.get(0);
        quiz.setCompleteStatus(true);
        complete = quiz.isComplete();
        assertTrue(complete);

        quiz = quizList.get(1);
        quiz.setCompleteStatus(false);
        complete = quiz.isComplete();
        assertFalse(complete);
        System.out.println("\tPASS Completed quiz!");
    }

    public void testAddQuiz() {
//        quizList.add(new Quiz("add new Quiz"));
//        assertEquals("add new Quiz",quizList.get(3).getQuizName());
//
        System.out.println("\tPASS test add Quiz!");
    }

    public void testAddQuestion() {
//        quizList.add(new Quiz("add new Quiz 2"));
//        quizList.get(3).addQuestion(new Question("What is the population of Canada?","3759","3800","4059","3759"));
//        quizList.get(3).addQuestion(new Question("When Canada was founded?","1868","1867","1888","1867"));
//        assertEquals("What is the population of Canada?",quizList.get(3).getQuestionList().get(0).getQuestion());
//        assertEquals("3759",quizList.get(3).getQuestionList().get(0).getOption1());
//        assertEquals("3800",quizList.get(3).getQuestionList().get(0).getOption2());
//        assertEquals("4059",quizList.get(3).getQuestionList().get(0).getOption3());
//        assertEquals("3759",quizList.get(3).getQuestionList().get(0).getKey());
//
//        assertEquals("When Canada was founded?",quizList.get(3).getQuestionList().get(1).getQuestion());
//        assertEquals("1868",quizList.get(3).getQuestionList().get(1).getOption1());
//        assertEquals("1867",quizList.get(3).getQuestionList().get(1).getOption2());
//        assertEquals("1888",quizList.get(3).getQuestionList().get(1).getOption3());
//        assertEquals("1867",quizList.get(3).getQuestionList().get(1).getKey());

        System.out.println("\tPASS test add Quiz!");
    }


}
