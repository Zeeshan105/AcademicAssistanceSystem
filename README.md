# COMP3350 Project
### Project name: Academic Assistance System
- GitHub repo link: https://github.com/Zeeshan105/AcademicAssistanceSystem
- The logs are provided in the logs.txt file in the .zip folder

- Group member:   Wen Qianyi, Sherba Daniel, Lui Ho, Javed Zeeshan, Chen Huayi 
- Environment:  Android 6.0 (Marshmallow, API level 23)
- Simulator: Nexus 7 tablet

- Major Features
    - Flashcard - Can be accessed from the home page when you open the app by pressing the “Flash Card” button.
       - To create a flash card, input a title and description, and specify the folder (category) it belongs to, and press “Submit”. If the folder name already exists in the              database, the flashcard will be added to it directly, otherwise a new folder is created.  
       - The “View Folders” button shows all created flashcard folders, which can be opened to view any flashcards inside of it.  
    - Quizzing System - Can be accessed from the home page when you open the app by pressing the “Quiz” button.
       - To create a quiz, press on the create quiz button and input a question, three possible options, and a valid answer, and the quiz name it belongs to, and press submit.            If the provided quiz name already exists in the database, the question is added to it directly, otherwise a new quiz is created.
       - To start quizzes, press the “Start Quizzes” button on the quiz home page, and select the appropriate quiz and press “Start Quiz” on the alert.
    - Quiz Statistics  - Can be accessed from the start quizzes activity by pressing the “View Stats” button.
       - The statistics page will show a list of all individual quizzes and their respective marks, along with general statistics about number of completed quizzes, average              grade, as well as highest and lowest grade. 
       - If no quizzes have been completed, the statistics page will not show any statistics beside specifying that 0 quizzes have been completed.
    - View Quiz Answers (new) - Can be accessed by pressing the view quiz answer button on the quiz activity.
       - The view quiz activity will show a list of all the quizzes. Press on any quiz, to view the answers to all the questions. You navigate to the next question by pressing            the next button, and return to the previous acitivity screen by pressing the back button on the android device
    
- Packages
    - application
        - Main.java
        - Services.java
    - business
        - Calculate.java
        - Validate.java
        - AccessQuiz.java
        - AccessFolder.java
    - database
        - DataAccess.java
        - DataAccessObject.java
    - object
        - CardFolder.java
        - FlashCard.java
        - Question.java
        - Quiz.java
    - presentation
        - AnswerActivity.java
        - CardListActivity.java
        - FlashCardActivity.java
        - FolderListActivity.java
        - MainActivity.java
        - MakeQuizActivity.java
        - QuizHomeActivity.java
        - SelectQuizActivity.java
        - StartQuizActivity.java
        - ViewQuizAnswerActivity.java
        - ViewStatsActivity.java


- Coding standards our group agreed to follow
   - Use ArrayLists for data storage. 
   - Use camel case for variables.
   - Use pascal case for classes.
   - Use snake case for xml files.
