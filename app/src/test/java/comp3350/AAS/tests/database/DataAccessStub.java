package comp3350.AAS.tests.database;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.Question;

public class DataAccessStub implements DataAccess {
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Quiz> quizList;
    private ArrayList<CardFolder> folderList;
    private ArrayList<String> completedQuizList;


    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String string) {
        Quiz quiz;
        Question question;
        CardFolder cardFolder;


        quizList = new ArrayList<Quiz>();
        quiz = new Quiz("Historical");
        quizList.add(quiz);
        quiz = new Quiz("Geographic");
        quizList.add(quiz);
        quiz = new Quiz("Math");
        quizList.add(quiz);

        question = new Question("Number of Canadians participating in World War II", "0.5 million", "1 million", "2 million", "1 million");
        addQuiz(question, "Historical");
        question = new Question("What year was the founding of Canada?", "1867", "1887", "1787", "1867");
        addQuiz(question, "Historical");
        question = new Question("Which aircraft manufacture belong to Canada?", "Airbus", "Boeing", "Bombardier", "Bombardier");
        addQuiz(question, "Historical");
        question = new Question("What is the area of Canada in square kilometers?","9.98 million","9.60 million","9.37 million","9.98 million");
        addQuiz(question, "Geographic");
        question = new Question("What is the capital city of Canada?","Vancouver","Ottawa","Toronto","Ottawa");
        addQuiz(question, "Geographic");
        question = new Question("What is the positive result of square root of 36?", "3", "6", "9", "6");
        addQuiz(question, "Math");
        question = new Question("What is the result of 1+1?", "0", "1", "2", "2");
        addQuiz(question, "Math");

        folderList=new ArrayList<>();

        addCard("National Land Area Descending Order", "Russia>Canada>China>America>Brazil...", "Geographic_Knowledge");
        addCard("Temperature Distributed", "Decreasing from low latitude to high latitude", "Geographic_Knowledge");

        addCard("First World War", "The World War I break out in 1914", "Historical_Event");
        addCard("Second World War", "The World War II break out in 1939", "Historical_Event");

        addCard("Addition and subtraction within 10", "1+1=2, 2+2=4", "Math_Practice");
        addCard("Calculate square root", "sqrt(1)=+-1, sqrt(16)=+-4, sqrt(36)=+-6, sqrt(-1)=undefined", "Math_Practice");

    }

    public void close() {
        System.out.println("Already Closed " +dbType +" database " +dbName);
    }

    public void addCard(String title, String desc, String folderName) {
        int index = -1; // Place holder index to check if the flashcard belongs to an existing or new flashcard folder.

        for(int i = 0; i < folderList.size();i++){
            if(folderList.get(i).getFolderName().equals(folderName)){
                index = i;
                break;
            }
        }

        if(index == -1){
            CardFolder newFolder = new CardFolder(folderName);
            newFolder.addCard(title,desc);
            folderList.add(newFolder);
        } else {
            folderList.get(index).addCard(title, desc);
        }
    }

    public void deleteCard(String folderName, String title){
        for(int i = 0; i < folderList.size();i++){
            if(folderList.get(i).getFolderName().equals(folderName)){
                for(int j =0; j < folderList.get(i).getCardList().size();i++){
                    if(folderList.get(i).getCardList().get(j).getTitle().equals(title)){
                        folderList.get(i).getCardList().remove(j);
                        return;
                    }
                }
            }
        }
    }

    public void getFolderList(ArrayList<CardFolder> folders) {
        folders.addAll(folderList);
    }

    public void deleteFolder(int index) {
        folderList.remove(index);
    }


    public void addQuiz(Question question, String name) {
        int index=-1; // Place holder index to check if the question belongs to an existing or new quiz.

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);
            if (selectedQuiz.getQuizName().compareTo(name)==0) {
                index=i;
                break;
            }
        }

        if (index==-1) {
            Quiz quiz = new Quiz(name);
            quiz.addQuestion(question);
            quizList.add(quiz);
        } else {
            Quiz quiz = quizList.get(index);
            quiz.addQuestion(question);
        }
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public ArrayList<String> getAllQuizName() {
        ArrayList<String> quizNames = new ArrayList<>();

        for (int i = 0; i < quizList.size(); i++) {
            quizNames.add(quizList.get(i).getQuizName());
        }
        return quizNames;
    }

    public ArrayList<String> generateQuizGradesList() {
//        ArrayList<String> completedQuizList = new ArrayList<String>();
        completedQuizList = new ArrayList<String>();
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).isComplete()) {
                completedQuizList.add( quizList.get(i).getQuizName() + "\nMark: " + (int)quizList.get(i).getQuizResult() + "/" + quizList.get(i).getQuestionCount() );
            }
        }
        return completedQuizList;
    }

    public String getNumCompletedQuiz(){
        int numberCompleted = 0;

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);

            if (selectedQuiz.isComplete()) {
                numberCompleted++;
            }
        }
        return "" + numberCompleted;
    }

    public void resetQuizStatus(String quizName, String status){
        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);

            if (selectedQuiz.getQuizName().equals(quizName)) {
                selectedQuiz.setCompleteStatus(Boolean.parseBoolean(status));
            }
        }
        completedQuizList.clear();
    }


    public void updateQuiz(String quizName, double grade){
        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);

            if (selectedQuiz.getQuizName().equals(quizName)) {
                selectedQuiz.setQuizResult(grade);
            }
        }
    }

    public String getAverageGrade(){
        double totalGrade = 0;
        int quizzesCompleted = 0;

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);

            if (selectedQuiz.isComplete()) {
                totalGrade += selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();
                quizzesCompleted++;
            }
        }

        double averageGrade = (totalGrade / quizzesCompleted) * 100.0;
        String rounded = String.format("%.2f", averageGrade);
        return rounded + "%";
    }

    public String getHighestGrade(){
        double highestGrade = Double.NEGATIVE_INFINITY; // Place holder value that is updated if there is a completed quiz.

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);
            double newGrade = selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();

            if (selectedQuiz.isComplete() && newGrade > highestGrade) {
                highestGrade = newGrade;
            }
        }

        String rounded = String.format("%.2f", highestGrade * 100.0);
        return rounded + "%";
    }

    public String getLowestGrade(){
        double lowestGrade = Double.POSITIVE_INFINITY; // Place holder value that is updated if there is a completed quiz.

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);
            double newGrade = selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();

            if (quizList.get(i).isComplete() && newGrade < lowestGrade) {
                lowestGrade = newGrade;
            }
        }

        String rounded = String.format("%.2f", lowestGrade * 100.0);
        return rounded + "%";
    }

}
