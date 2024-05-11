package Codsoft;
import java.util.*;
class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctoption;

    public QuizQuestion(String question,List<String> options,int correctoption){
        this.question = question;
        this.options = options;
        this.correctoption = correctoption;
    }
    public String getQuestion(){
        return question;
    }
    public List<String> getOptions(){
        return options;
    }
    public int getCorrectOption(){
        return correctoption;
    }
}
public class Main{
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private Scanner scanner;

    public Main(){
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        timer = new Timer();
        scanner = new Scanner(System.in);
    }
    public void addQuestion(QuizQuestion question){
        questions.add(question);
    }
    public void startQuiz(){
        System.out.println("Welcome to the Quiz");
        timer.schedule(new QuizTimerTask(),3,700000);

        while(currentQuestionIndex < questions.size()){
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);
            int userChoice = getUserChoice();
            if(userChoice == currentQuestion.getCorrectOption()){
                System.out.println("correct");
                score++;
            }
            else{
                System.out.println("Incorrect");
            }
            currentQuestionIndex++;
        }
        displayResult();
    }
    private void displayQuestion(QuizQuestion question){
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for(int i=0; i<options.size(); i++){
            System.out.println((i+1) + ". "+options.get(i));
        }
    }
    private int getUserChoice(){
        System.out.println("Enter your choice(1-" +questions.get(currentQuestionIndex).getOptions().size() + "): ");
        return scanner.nextInt();
    }
    private void displayResult(){
        System.out.println("Quiz completed!");
        System.out.println("Your score:" +score +"/" +questions.size());
    }
    private class QuizTimerTask extends TimerTask{
        private int secondsLeft = 10;
        @Override
        public void run(){
            if(secondsLeft > 0){
                System.out.println("Time left:" +secondsLeft +" seconds");
                secondsLeft--;
            }
            else{
                System.out.println("Time's up!");
                currentQuestionIndex++;
            }
        }
    }
    public static void main(String args[]){
        Main quizApp = new Main();
        quizApp.addQuestion(new QuizQuestion("1.What is the capital of Andhra Pradesh",List.of("Amaravati","lucknow","vijayawada","srikalkulam"), 1));
        quizApp.addQuestion(new QuizQuestion("2.Which Animal is known as the Ship of Desert", List.of("Wolf","Camel","Kangarooo","none"), 2));
        quizApp.addQuestion(new QuizQuestion("3.How many days are there in a week", List.of("7","4","8","30"), 1));
        quizApp.addQuestion(new QuizQuestion("4.Rainbow consists of how many colours", List.of("12","9","7","infinite"), 3));
        quizApp.addQuestion(new QuizQuestion("5.Who heads the RBI", List.of("Minister","Governer","Collector","None"), 2));
        quizApp.addQuestion(new QuizQuestion("6.What is the national bird of india", List.of("Peacock","Sparrow","Parrot","Crow"), 1));
        quizApp.addQuestion(new QuizQuestion("7.What is the national flag of india", List.of("fourcolour","twocolour","fiveclour","tricolour"), 4));
        quizApp.addQuestion(new QuizQuestion("8.Who created bitcoin", List.of("Santoshi Nakamota","Narendra Modi","Gandhiji","Nehru"), 1));
        quizApp.addQuestion(new QuizQuestion("9.What is the currency of india", List.of("dollar","bitcoin","rupee","All of the above0"), 3));
        quizApp.addQuestion(new QuizQuestion("10.When is India's independence day?", List.of("January 22","November 14","September 2","August 15"), 4));
        
        
        quizApp.startQuiz();        


    }
}
