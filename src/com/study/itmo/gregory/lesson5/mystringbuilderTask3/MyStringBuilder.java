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
    private List deeds = new List();

    public MyStringBuilder undo(){

        deeds.getLast().undo();

        return this;

    }


    public MyStringBuilder(String str){
        stringBuilder = new StringBuilder(str);
    }

    public MyStringBuilder append(String str) {

        stringBuilder.append(str);

        //todo тут дописывай свое действие
        //так можно кастомайзить поведения если не заовверрайдить
        //стрингбилдер (выше поле) хранит стринг с которым мы работаем
        //далее просто вызови его туСтринг

        deeds.add(new Container(str.length()){
            @Override
            public void undo() {

                stringBuilder.delete(
                        stringBuilder.length() - tmp,
                        stringBuilder.length());
            }
        });

        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
