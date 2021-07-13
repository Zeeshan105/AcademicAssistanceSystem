package comp3350.AAS.tests.database;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.database.DataAccessObject;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.database.DataAccess;

import static org.junit.Assert.assertNotNull;

public class DataAccessTest extends TestCase {
    private DataAccess dataAccess;
    private ArrayList<CardFolder> folderList;
    private ArrayList<Quiz> quizList;
    private Quiz quiz1, quiz2, quiz3, quiz4;
    private Question question;

    public DataAccessTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");
        dataAccess = new DataAccessStub();
        dataAccess.open("stub");

        folderList=new ArrayList<>();
        dataAccess.getFolderList(folderList);

        quizList = dataAccess.getQuizList();
    }

    public void tearDown() {
        dataAccess.close();
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }


    public void testFirstFolder(){
        ArrayList<FlashCard> flashCards = folderList.get(0).getCardList();
        assertNotNull(flashCards);
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
        assertNotNull(flashCards);
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
        assertNotNull(flashCards);
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
        folderList = new ArrayList<>();
        dataAccess.addCard("title1", "description", "newFolder");
        dataAccess.addCard("title2", "description", "newFolder");
        dataAccess.addCard("title3", "description", "newFolder");

        dataAccess.getFolderList(folderList);
        assertNotNull(folderList);
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
        folderList = new ArrayList<>();
        dataAccess.addCard("title1", "description1", "newFolder");
        dataAccess.addCard("title2", "description2", "newFolder");
        dataAccess.addCard("title3", "description3", "newFolder");

        dataAccess.getFolderList(folderList);
        assertNotNull(folderList);

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
        folderList = new ArrayList<>();
        dataAccess.addCard("title1", "description", "newFolder1");
        dataAccess.addCard("title2", "description", "newFolder2");
        dataAccess.addCard("title3", "description", "newFolder3");

        dataAccess.getFolderList(folderList);
        assertNotNull(folderList);
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
    public void testDeleteCardAndfolderInf(){
        folderList = new ArrayList<>();
        dataAccess.addCard("addNew CardA", "descriptionA", "newCard");
        dataAccess.addCard("addNew CardB", "descriptionB", "newCard");
        dataAccess.getFolderList(folderList);
        assertNotNull(folderList);
        CardFolder folders;
        folders = folderList.get(3);
        assertEquals("addNew CardA",folders.getCardTitles().get(0));
        assertEquals("descriptionA",folders.getCardDescription().get(0));
        assertEquals("newCard",folders.getFolderName());
        dataAccess.deleteCard("newCard","addNew CardA");
        assertEquals("addNew CardB",folders.getCardTitles().get(0));
        assertEquals("descriptionB",folders.getCardDescription().get(0));
        assertEquals("newCard",folders.getFolderName());
        System.out.println("\tPASS test Delete Card!");
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
        Question newQuestion = new Question("What is the population of Canada?","3759","3800","4059","3759");
        dataAccess.addQuiz(newQuestion, "add new Quiz");
        ArrayList<Quiz> newQuiz = dataAccess.getQuizList();
        assertEquals("add new Quiz",newQuiz.get(3).getQuizName());
        dataAccess.addQuiz(newQuestion, "add new Quiz 2");
        assertEquals("add new Quiz 2",newQuiz.get(4).getQuizName());
        System.out.println("\tPASS test add Quiz!");
    }

    public void testAddQuestion() {
        Question newQuestion;

        newQuestion = new Question("When Canada was founded?","1868","1867","1888","1867");
        dataAccess.addQuiz(newQuestion, "add new Quiz 3");

        ArrayList<Quiz> newQuiz = dataAccess.getQuizList();
        assertNotNull(newQuiz);
        assertEquals("When Canada was founded?",newQuiz.get(3).getQuestionList().get(0).getQuestion());
        assertEquals("1868",newQuiz.get(3).getQuestionList().get(0).getOption1());
        assertEquals("1867",newQuiz.get(3).getQuestionList().get(0).getOption2());
        assertEquals("1888",newQuiz.get(3).getQuestionList().get(0).getOption3());
        assertEquals("1867",newQuiz.get(3).getQuestionList().get(0).getKey());

        newQuestion = new Question("What is the population of Canada?","3759","3770","4029","3359");
        dataAccess.addQuiz(newQuestion, "add new Quiz 3");

        assertEquals("What is the population of Canada?",newQuiz.get(3).getQuestionList().get(1).getQuestion());
        assertEquals("3759",newQuiz.get(3).getQuestionList().get(1).getOption1());
        assertEquals("3770",newQuiz.get(3).getQuestionList().get(1).getOption2());
        assertEquals("4029",newQuiz.get(3).getQuestionList().get(1).getOption3());
        assertEquals("3359",newQuiz.get(3).getQuestionList().get(1).getKey());

        System.out.println("\tPASS test add Question!");
    }

    public void testDB(){
        assertNotNull(dataAccess.generateQuizGradesList());
        System.out.println("\tPASS test DB!");
    }

    public void testUpdateQuiz(){
        Question newQuestion = new Question("Testing update grade","A","B","C","A");
        dataAccess.addQuiz(newQuestion, "Quiz - test to Update");

        dataAccess.getQuizList().get(3).setQuizResult(10086);
        assertEquals(10086.0, dataAccess.getQuizList().get(3).getQuizResult());

        dataAccess.updateQuiz("Quiz - test to Update",3);
        assertEquals(3.0, dataAccess.getQuizList().get(3).getQuizResult());

        dataAccess.updateQuiz("Quiz - test to Update",1);
        assertEquals(1.0, dataAccess.getQuizList().get(3).getQuizResult());

        assertEquals(0.0, dataAccess.getQuizList().get(0).getQuizResult());
        dataAccess.updateQuiz(dataAccess.getQuizList().get(0).getQuizName(), 1);
        assertEquals(1.0, dataAccess.getQuizList().get(0).getQuizResult());
        System.out.println("\tPASS test Complete Quiz!");
    }

    public void testOneZeroGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testOneTypicalGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(0.6);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("60.00%", dataAccess.getAverageGrade());
        assertEquals("60.00%", dataAccess.getHighestGrade());
        assertEquals("60.00%", dataAccess.getLowestGrade());
    }

    public void testOneFullGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

    public void testTwoZeroQuizGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testTwoTypicalGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(0.8);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0.5);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("65.00%", dataAccess.getAverageGrade());
        assertEquals("80.00%", dataAccess.getHighestGrade());
        assertEquals("50.00%", dataAccess.getLowestGrade());
    }

    public void testTwoFullGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(1);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

    public void testMultiZeroQuizGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(0);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(0);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testMultiTypicalGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(0.2);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0.75);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(0.98);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(0.5);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("60.75%", dataAccess.getAverageGrade());
        assertEquals("98.00%", dataAccess.getHighestGrade());
        assertEquals("20.00%", dataAccess.getLowestGrade());
    }

    public void testMultiFullGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(1);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(1);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(1);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

    public void testInvalidGrade(){
        quiz1=new Quiz("quiz1");

        quiz1.setCompleteStatus(true);

        quiz1.setQuizResult(-999);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

}
