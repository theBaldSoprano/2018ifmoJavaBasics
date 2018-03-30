package com.study.itmo.gregory.lesson5;



public class Solution  {

/*todo создать свой класс МойСтрингБилдер
у него будет свой метод аппэнд
внутри которого будет сначала выполняться аппенд делигируемый стрингу
а потом в очередь (линкедлист) будет добавляться действие обратное аппенду
и потом выполняя отмену действия будет выполняться
 */

class MyStringBuilder{


    private StringBuilder stringBuilder;


    public MyStringBuilder append(String str) {

        stringBuilder.append(str);

        //todo тут дописывай свое действие
        //так можно кастомайзить поведения если не заовверрайдить
        //стрингбилдер (выше поле) хранит стринг с которым мы работаем
        //далее просто вызови его туСтринг

        return this;
    }

    //перегрузить конструктор
}

public static void main(String[] args) {
    String s = "foo";


}


}
