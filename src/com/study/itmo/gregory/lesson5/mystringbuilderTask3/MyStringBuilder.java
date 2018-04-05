package com.study.itmo.gregory.lesson5.mystringbuilderTask3;

import com.study.itmo.gregory.lesson2.Container;
import com.study.itmo.gregory.lesson2.list.List;

public class MyStringBuilder {

    /*todo создать свой класс МойСтрингБилдер
у него будет свой метод аппэнд
внутри которого будет сначала выполняться аппенд делигируемый стрингбилдеру
а потом в очередь (линкедлист) будет добавляться действие обратное аппенду
и потом выполняя отмену действия будет выполняться
 */

    private StringBuilder stringBuilder;
    private List<Container> deeds = new List<>();
    private List<Listener> listeners = new List<>();

    public MyStringBuilder undo() {

        deeds.getLast().getData().undo();

        notifyListeners();

        //todo удаление последнего элемента

        return this;
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void notifyListeners(){
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).getData().onChange();
            //fixme сделать чтобы так не было
        }
    }

    public MyStringBuilder(String str) {
        stringBuilder = new StringBuilder(str);
    }

    public MyStringBuilder append(String str) {

        stringBuilder.append(str);

        notifyListeners();

        //todo тут дописывай свое действие
        //так можно кастомайзить поведения если не заовверрайдить
        //стрингбилдер (выше поле) хранит стринг с которым мы работаем
        //далее просто вызови его туСтринг


        deeds.add(() ->
                stringBuilder.delete(
                stringBuilder.length() - str.length(),
                stringBuilder.length())
        );

        return this;
//        todo посмотреть другие языки на jvm
        // гугл лямбда-исчисления
        // грамматика Языка программирования
        // generics
        //
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
