package com.adaptionsoft.games;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class QuestionPool {
    private static enum QuestionType{
        POP("Pop", ImmutableSet.of(0,4,8)),
        SCIENCE("Science", ImmutableSet.of(1,5,9)),
        SPORTS("Sports", ImmutableSet.of(2,6,10)),
        ROCK("Rock", ImmutableSet.of());

        QuestionType(String name, Set<Integer> positionSet) {
            this.name = name;
            this.positionSet = positionSet;
        }

        private String name;
        private Set<Integer> positionSet;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Integer> getPositionSet() {
            return positionSet;
        }

        public void setPositionSet(Set<Integer> positionSet) {
            this.positionSet = positionSet;
        }

        public Boolean hitPosition(int position){
            return positionSet.contains(position);
        }
    }

    private Map<String,LinkedList<String>> questionsMap = Maps.newHashMap();

    public QuestionPool() {
        initQuestions();
    }

    private void initQuestions() {
        for (int index = 0; index < QuestionType.values().length; index++) {
            QuestionType questionType = QuestionType.values()[index];
            LinkedList<String> questions = questionsMap.get(questionType);
            if (questions == null) {
                questions = new LinkedList<>();
                questionsMap.put(questionType.getName(), questions);
            }
            for (int i = 0; i < 50; i++) {
                questions.add(questionType.getName() + " Question " + i);
            }
        }
    }

    public void askQuestion(int position){

        for (int index = 0; index < QuestionType.values().length; index++) {
            QuestionType questionType = QuestionType.values()[index];
            if(questionType.hitPosition(position)){
                doAskQuestion(questionType);
                return;
            }
        }

        // Rock兜底
        doAskQuestion(QuestionType.ROCK);
    }

    private void doAskQuestion(QuestionType questionType) {
        LinkedList<String> questions = questionsMap.get(questionType.getName());
        System.out.println("The category is " + questionType.getName());
        System.out.println(questions.removeFirst());
    }


}
