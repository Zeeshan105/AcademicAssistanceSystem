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
    private static ArrayList<CardFolder> folderList;
    private static ArrayList<Quiz> quizList;

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



    public static void dataAccessTest(DataAccess dataAccess){
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;

        folderList=new ArrayList<>();
        dataAccess.getFolderList(folderList);

        quizList = dataAccess.getQuizList();

        dataAccessTest.testFirstFolder();
        dataAccessTest.testSecondFolder();
        dataAccessTest.testThirdFolder();
        dataAccessTest.testAddToSameFolderWithSameDescription();
        dataAccessTest.testAddToSameFolderWithVaryDescription();
        dataAccessTest.testAddToDifferentFolder();
        dataAccessTest.testAddAndDeleteCard();
        dataAccessTest.testAddAndDeleteQuiz();
        dataAccessTest.testQuizName();
        dataAccessTest.testFirstQuiz();
        dataAccessTest.testSecondQuiz();
        dataAccessTest.testThirdQuiz();
        dataAccessTest.testThirdFolder();
        dataAccessTest.testCompletedQuiz();
        dataAccessTest.testDB();
        dataAccessTest.testOneZeroGrade();
        dataAccessTest.testOneTypicalGrade();
        dataAccessTest.testOneFullGrade();
        dataAccessTest.testTwoZeroQuizGrade();
        dataAccessTest.testTwoTypicalGrade();
        dataAccessTest.testTwoFullGrade();
        dataAccessTest.testMultiZeroQuizGrade();
        dataAccessTest.testMultiTypicalGrade();
        dataAccessTest.testMultiFullGrade();

        dataAccess.updateQuiz("Historical", 0);
        dataAccess.updateQuiz("Math", 0);
        dataAccess.updateQuiz("Geographic", 0);
        dataAccess.updateQuiz("add new Quiz 3", 0);
        dataAccess.resetQuizStatus("Historical", "False");
        dataAccess.resetQuizStatus("Math", "False");
        dataAccess.resetQuizStatus("Geographic", "False");
        dataAccess.resetQuizStatus("add new Quiz 3", "False");
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

    public void testAddAndDeleteCard(){
        folderList = new ArrayList<>();
        dataAccess.addCard("addNew CardA", "descriptionA", "newCard");
        dataAccess.addCard("addNew CardB", "descriptionB", "newCard");
        dataAccess.getFolderList(folderList);
        assertNotNull(folderList);

        CardFolder folders;
        folders = folderList.get(3);
        assertEquals("newCard",folders.getFolderName());
        assertEquals("addNew CardA",folders.getCardTitles().get(0));
        assertEquals("descriptionA",folders.getCardDescription().get(0));
        assertEquals("addNew CardB",folders.getCardTitles().get(1));
        assertEquals("descriptionB",folders.getCardDescription().get(1));

        dataAccess.deleteCard("newCard","addNew CardA");
        dataAccess.deleteCard("newCard","addNew CardB");

        System.out.println("\tPASS test Delete Card!");
    }

    public void testAddAndDeleteQuiz() {
        Question newQuestion;

        newQuestion = new Question("a Question1","optionA","optionB","optionC","optionA");
        dataAccess.addQuiz(newQuestion, "add new Quiz 4");
        newQuestion = new Question("a Question2","True","False","","True");
        dataAccess.addQuiz(newQuestion, "add new Quiz 4");

        quizList = dataAccess.getQuizList();
        ArrayList<Quiz> newQuiz = quizList;
        ArrayList<Question> questions = newQuiz.get(4).getQuestionList();
        Question question;
        assertNotNull(questions);

        assertEquals("add new Quiz 4", newQuiz.get(4).getQuizName());

        question=questions.get(0);
        assertEquals("a Question1", question.getQuestion());
        assertEquals("optionA", question.getKey());

        question=questions.get(1);
        assertEquals("a Question2", question.getQuestion());
        assertEquals("True", question.getKey());

        dataAccess.deleteQuiz(4);

        System.out.println("\tPASS test add Question!");
    }

    public void testQuizName(){
        ArrayList<String> quizNameList = dataAccess.getAllQuizName();

        assertEquals("Historical", quizNameList.get(0));
        assertEquals("Math", quizNameList.get(1));
        assertEquals("Geographic", quizNameList.get(2));
        assertEquals("add new Quiz 3", quizNameList.get(3));
        System.out.println("\tPASS test quiz name!");
    }

    public void testFirstQuiz(){
        ArrayList<Question> questions = quizList.get(0).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("Number of Canadians participating in World War II", question.getQuestion());
        assertEquals("A.0.5 million", question.getOption1());
        assertEquals("B.1 million", question.getOption2());
        assertEquals("C.2 million", question.getOption3());
        assertEquals("1 million", question.getKey());

        question=questions.get(1);
        assertEquals("The capital city of Canada is Vancouver", question.getQuestion());
        assertEquals("False", question.getKey());

        question=questions.get(2);
        assertEquals("The year of the founding of Canada is 1867", question.getQuestion());
        assertEquals("True", question.getKey());

        question=questions.get(3);
        assertEquals("What year was the founding of Canada?", question.getQuestion());
        assertEquals("A.1867", question.getOption1());
        assertEquals("B.1887", question.getOption2());
        assertEquals("C.1787", question.getOption3());
        assertEquals("1867", question.getKey());

        question=questions.get(4);
        assertEquals("Which aircraft manufacture belong to Canada?", question.getQuestion());
        assertEquals("A.Airbus", question.getOption1());
        assertEquals("B.Boeing", question.getOption2());
        assertEquals("C.Bombardier", question.getOption3());
        assertEquals("Bombardier", question.getKey());

        System.out.println("\tPASS test 1st quiz!");
    }

    public void testSecondQuiz(){
        ArrayList<Question> questions = quizList.get(1).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("The positive result of square root of 16 is 3", question.getQuestion());
        assertEquals("False", question.getKey());

        question=questions.get(1);
        assertEquals("The positive result of square root of 25 is 5", question.getQuestion());
        assertEquals("True", question.getKey());

        question=questions.get(2);
        assertEquals("What is the positive result of square root of 36?", question.getQuestion());
        assertEquals("A.3", question.getOption1());
        assertEquals("B.6", question.getOption2());
        assertEquals("C.9", question.getOption3());
        assertEquals("6", question.getKey());

        question=questions.get(3);
        assertEquals("What is the result of 1+1?", question.getQuestion());
        assertEquals("A.0", question.getOption1());
        assertEquals("B.1", question.getOption2());
        assertEquals("C.2", question.getOption3());
        assertEquals("2", question.getKey());

        System.out.println("\tPASS test 2nd quiz!");
    }

    public void testThirdQuiz(){
        ArrayList<Question> questions = quizList.get(2).getQuestionList();
        Question question;

        question=questions.get(0);
        assertEquals("What is the area of Canada in square kilometers?", question.getQuestion());
        assertEquals("A.9.98 million", question.getOption1());
        assertEquals("B.9.60 million", question.getOption2());
        assertEquals("C.9.37 million", question.getOption3());
        assertEquals("9.98 million", question.getKey());

        question=questions.get(1);
        assertEquals("What is the capital city of Canada?", question.getQuestion());
        assertEquals("A.Vancouver", question.getOption1());
        assertEquals("B.Ottawa", question.getOption2());
        assertEquals("C.Toronto", question.getOption3());
        assertEquals("Ottawa", question.getKey());

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

    public void testDB(){
        assertNotNull(dataAccess.generateQuizGradesList());
        System.out.println("\tPASS test DB!");
    }

    public void testOneZeroGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 0);   // The total question size is 5, we test 0/5 which is 0%

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testOneTypicalGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 3);  // The total question size is 5, we test 3/5 which is 60%

        assertEquals("60.00%", dataAccess.getAverageGrade());
        assertEquals("60.00%", dataAccess.getHighestGrade());
        assertEquals("60.00%", dataAccess.getLowestGrade());
    }

    public void testOneFullGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 5);  // The total question size is 5, we test 5/5 which is 100%

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

    public void testTwoZeroQuizGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 0);  // The total question size is 5, we test 0/5 which is 0%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 0);  // The total question size is 4, we test 0/4 which is 0%

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testTwoTypicalGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 4);  // The total question size is 5, we test 4/5 which is 80%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 2);  // The total question size is 4, we test 2/4 which is 50%

        assertEquals("65.00%", dataAccess.getAverageGrade());
        assertEquals("80.00%", dataAccess.getHighestGrade());
        assertEquals("50.00%", dataAccess.getLowestGrade());
    }

    public void testTwoFullGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 5);  // The total question size is 5, we test 5/5 which is 100%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 4);  // The total question size is 4, we test 4/4 which is 100%

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

    public void testMultiZeroQuizGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 0);  // The total question size is 5, we test 0/5 which is 0%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 0);  // The total question size is 4, we test 0/4 which is 0%

        dataAccess.resetQuizStatus("Geographic", "True");
        dataAccess.updateQuiz("Geographic", 0);  // The total question size is 2, we test 0/2 which is 0%

        dataAccess.resetQuizStatus("add new Quiz 3", "True");
        dataAccess.updateQuiz("add new Quiz 3", 0);  // The total question size is 2, we test 0/2 which is 0%

        assertEquals("0.00%", dataAccess.getAverageGrade());
        assertEquals("0.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testMultiTypicalGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 2);  // The total question size is 5, we test 2/5 which is 40%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 3);  // The total question size is 4, we test 3/4 which is 75%

        dataAccess.resetQuizStatus("Geographic", "True");
        dataAccess.updateQuiz("Geographic", 2);  // The total question size is 2, we test 2/2 which is 100%

        dataAccess.resetQuizStatus("add new Quiz 3", "True");
        dataAccess.updateQuiz("add new Quiz 3", 0);  // The total question size is 2, we test 0/2 which is 0%

        assertEquals("53.75%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("0.00%", dataAccess.getLowestGrade());
    }

    public void testMultiFullGrade(){
        dataAccess.resetQuizStatus("Historical", "True");
        dataAccess.updateQuiz("Historical", 5);  // The total question size is 5, we test 5/5 which is 100%

        dataAccess.resetQuizStatus("Math", "True");
        dataAccess.updateQuiz("Math", 4);  // The total question size is 4, we test 4/4 which is 100%

        dataAccess.resetQuizStatus("Geographic", "True");
        dataAccess.updateQuiz("Geographic", 2);  // The total question size is 2, we test 2/2 which is 100%

        dataAccess.resetQuizStatus("add new Quiz 3", "True");
        dataAccess.updateQuiz("add new Quiz 3", 2);  // The total question size is 2, we test 2/2 which is 100%

        assertEquals("100.00%", dataAccess.getAverageGrade());
        assertEquals("100.00%", dataAccess.getHighestGrade());
        assertEquals("100.00%", dataAccess.getLowestGrade());
    }

}
