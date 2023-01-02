package org.lois.logic.parser.tree;

////////////////////////////////////////////
//Лабораторная работа №1-2 по дисциплине ЛОИС
//Выполнено студентом группы 921703
//Василевский Артемий Дмитриевич
//Использованные источники:
//1) Справочно система по дисциплине ЛОИС
public class LETree {
    private final LENodeOld root;

    public LETree(LENodeOld root) {
        this.root = root;
    }

    public LENodeOld getRoot() {
        return root;
    }

}
