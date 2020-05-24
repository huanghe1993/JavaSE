# java 泛型

<a name="f6ost"></a>
## 一、不使用泛型

<br />
<br />一说到泛型，大伙肯定不会陌生，我们代码里面有很多类似这样的语句：
```java
List<String> list=new ArrayList<>();
```
ArrayList就是个泛型类，我们通过设定不同的类型，可以往集合里面存储不同类型的数据类型（而且只能存储设定的数据类型，这是泛型的优势之一）。“泛型”简单的意思就是泛指的类型（参数化类型）。

想象下这样的场景：如果我们现在要写一个容器类（支持数据增删查询的），我们写了支持String类型的，后面还需要写支持Integer类型的。然后呢？Doubel、Float、各种自定义类型？这样重复代码太多了，而且这些容器的算法都是一致的。我们可以通过泛指一种类型T,来代替我们之前需要的所有类型，把我们需要的类型作为参数传递到容器里面，这样我们算法只需要写一套就可以适应所有的类型。最典型的的例子就是ArrayList了，这个集合我们无论传递什么数据类型，它都能很好的工作。
```java
class MyList{
    private Object[] elements=new Object[10];
    private int size;
    
    public void add(Object item) {
    	elements[size++]=item;
    }
    
    public Object get(int index) {
    	return elements[index];
    }
}

```

<br />这个代码灵活性很高，所有的类型都可以向上转型为Object类，这样我们就可以往里面存储各种类型的数据了。的确Java在泛型出现之前，也是这么做的。

:::info
但是这样的有一个问题：如果集合里面数据很多，某一个数据转型出现错误，在编译期是无法发现的。但是在运行期会发生 java.lang.ClassCastException。
:::


```java
MyList myList=new MyList();
myList.add("A");
myList.add(1);
System.out.println(myList.get(0));
System.out.println((String)myList.get(1));

```
我们在这个集合里面存储了多个类型（某些情况下容器可能会存储多种类型的数据），如果数据量较多，转型的时候难免会出现异常，而这些都是无法在编译期得知的。而泛型一方面让我们只能往集合中添加一种类型的数据，同时可以让我们在编译期就发现这些错误，避免运行时异常的发生，提升代码的健壮性。<br />

<a name="yyYpY"></a>
## 二、Java泛型介绍

<br />下面我们来介绍Java泛型的相关内容，下面会介绍以下几个方面：

- Java泛型类
- Java泛型方法
- Java泛型接口
- Java泛型擦除及其相关内容
- Java泛型通配符



<a name="FVVMk"></a>
### 2.1 泛型类
类结构是面向对象中最基本的元素，如果我们的类需要有很好的扩展性，那么我们可以将其设置成泛型的。假设我们需要一个栈的数据结构，我们通过栈的例子看泛型类：
```java
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class Generic<T>{ 
    //key这个成员变量的类型为T,T的类型由外部指定  
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}
```
```java
//泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
//传入的实参类型需与泛型的类型参数类型相同，即为Integer.
Generic<Integer> genericInteger = new Generic<Integer>(123456);

//传入的实参类型需与泛型的类型参数类型相同，即为String.
Generic<String> genericString = new Generic<String>("key_vlaue");
Log.d("泛型测试","key is " + genericInteger.getKey());
Log.d("泛型测试","key is " + genericString.getKey());
```

<br />定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
```java
Generic generic = new Generic("111111");
Generic generic1 = new Generic(4444);
Generic generic2 = new Generic(55.55);
Generic generic3 = new Generic(false);

Log.d("泛型测试","key is " + generic.getKey());
Log.d("泛型测试","key is " + generic1.getKey());
Log.d("泛型测试","key is " + generic2.getKey());
Log.d("泛型测试","key is " + generic3.getKey());
```
:::info
注意：

  1. 泛型的类型参数只能是类类型，不能是简单类型。
  1. 不能对确切的泛型类型使用instanceof操作。如下面的操作是非法的，编译时会出错。
```java
if(ex_num instanceof Generic<Number>){   
}
```
:::

<br />

<a name="opC9O"></a>
### 2.2 泛型接口
泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中，可以看一个例子：
```java
//定义一个泛型接口
public interface Generator<T> {
    public T next();
}
```
当实现泛型接口的类，未传入泛型实参时：
```java
/**
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * 即：class FruitGenerator<T> implements Generator<T>{
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错："Unknown class"
 */
class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}
```
当实现泛型接口的类，传入泛型实参时：
```java
/**
 * 传入泛型实参时：
 * 定义一个生产器实现这个接口,虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
 */
public class FruitGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}
```
<a name="gCsU7"></a>
### 2.3 泛型通配符

<br />我们知道Ingeter是Number的一个子类，在使用Generic<Number>作为形参的方法中，能否使用Generic<Ingeter>的实例传入呢？在逻辑上类似于Generic<Number>和Generic<Ingeter>是否可以看成具有父子关系的泛型类型呢？<br />
<br />为了弄清楚这个问题，我们使用`Generic<T>`这个泛型类继续看下面的例子：
```java
public void showKeyValue1(Generic<Number> obj){
    Log.d("泛型测试","key value is " + obj.getKey());
}
```
```java
Generic<Integer> gInteger = new Generic<Integer>(123);
Generic<Number> gNumber = new Generic<Number>(456);

showKeyValue(gNumber);

// showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer> 
// cannot be applied to Generic<java.lang.Number>
// showKeyValue(gInteger);

```
通过提示信息我们可以看到Generic<Integer>不能被看作为`Generic<Number>的子类。由此可以看出：同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。<br />
<br />回到上面的例子，如何解决上面的问题？总不能为了定义一个新的方法来处理Generic<Integer>类型的类，这显然与java中的多台理念相违背。因此我们需要一个在逻辑上可以表示同时是Generic<Integer>和Generic<Number>父类的引用类型。由此类型通配符应运而生。<br />

```java
public void showKeyValue1(Generic<?> obj){
    Log.d("泛型测试","key value is " + obj.getKey());
}
```

<br />类型通配符一般是使用？代替具体的类型实参，注意了，**此处’？’是类型实参，而不是类型形参 。重要说三遍！此处’？’是类型实参，而不是类型形参 ！** 此处’？’是类型实参，而不是类型形参 ！再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真实的类型。<br />
<br />可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。<br />

<a name="a36652f6"></a>
## 三、泛型方法

<br />在java中,泛型类的定义非常简单，但是泛型方法就比较复杂了。

**泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型** 。

```java
/**
 * 泛型方法的基本介绍
 * @param tClass 传入的泛型实参
 * @return T 返回值为T类型
 * 说明：
 *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
 *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
 *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
 *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
 */
public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
  IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
}

Object obj = genericMethod(Class.forName("com.test.test"));
```
<br />
:::info
只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
:::

<br />比如上面的：<br />

```java
public void showKeyValue1(Generic<?> obj){
    Log.d("泛型测试","key value is " + obj.getKey());
}
```

<br />这个就不是泛型方法，因为使用了泛型的成员方法，并没有声明泛型<T>

<a name="1b80c614"></a>
### 3.1 泛型方法的基本用法
光看上面的例子有的同学可能依然会非常迷糊，我们再通过一个例子，把我泛型方法再总结一下。
```java
package com.huanghe.generic.method;

import sun.rmi.runtime.Log;

/**
 * @author River
 * @date 2020/5/24 9:37
 * @description 泛型方法的基本用法
 */
public class GenericBasicUse {

    /**
     * 泛型类
     *
     * @param <T>
     */
    public class Generic<T> {

        private T key;

        public Generic(T key) {
            this.key = key;
        }

        /**
         * 虽然在方法中使用了泛型，但是这并不是一个泛型方法。
         * 这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
         * 所以在这个方法中才可以继续使用 T 这个泛型。
         * @return
         */
        public T getKey(){
            return key;
        }

    }

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * @param container
     * @param <T>
     * @return
     */
    public <T> T showKeyName(Generic<T> container){
        System.out.println("container key :" + container.getKey());
        T test = container.getKey();
        return test;
    }

    /**
     * 这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
     * @param obj
     */
    public void showKeyValue1(Generic<Number> obj){
        System.out.println("key value is " + obj.getKey());
    }

    /**
     *  这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
     *  同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
     * @param obj
     */
    public void showKeyValue2(Generic<?> obj){
        System.out.println("key value is " + obj.getKey());
    }
}

```
<a name="4eece572"></a>
### 3.2 类中的泛型方法

<br />当然这并不是泛型方法的全部，泛型方法可以出现杂任何地方和任何场景中使用。但是有一种情况是非常特殊的，当泛型方法出现在泛型类中时，我们再通过一个例子看一下
```java
/**
 * @author River
 * @date 2020/5/24 9:47
 * @description 类中的泛型方法
 */
public class GenericMethodInClass {

    class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

    class Apple extends Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }

    class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    class GenerateTest<T>{

        public void show_1(T t){
            System.out.println(t.toString());
        }

        // 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        // 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }

        // 在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t){
            System.out.println(t.toString());
        }
    }

    public  void test() {
        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        //apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(person);

        //使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);

        //使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);
    }
}
```
<a name="5f5d9970"></a>
### 3.3 泛型方法与可变参数
```java
public <T> void printMsg( T... args){
    for(T t : args){
        Log.d("泛型测试","t is " + t);
    }
}
```
<a name="0fcbc260"></a>
### 3.4 静态方法与泛型

<br />静态方法有一种情况需要注意一下，那就是在类中的静态方法使用泛型：**静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。**<br />即：**如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法** 。
```java
public class GenericStasticMethod {

    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     * "StaticGenerator cannot be refrenced from static context"
     */
    public static <T> void show(T t) {

    }

}
```
<a name="f334a2ad"></a>
## 四、泛型上下边界
在使用泛型的时候，我们还可以为传入的泛型类型实参进行上下边界的限制，如：类型实参只准传入某种类型的父类或某种类型的子类。

- 为泛型添加上边界，即传入的类型实参必须是指定类型的子类型
```java
public void showKeyValue1(Generic<? extends Number> obj){
    Log.d("泛型测试","key value is " + obj.getKey());
}
```
```java
Generic<String> generic1 = new Generic<String>("11111");
Generic<Integer> generic2 = new Generic<Integer>(2222);
Generic<Float> generic3 = new Generic<Float>(2.4f);
Generic<Double> generic4 = new Generic<Double>(2.56);

//这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
//showKeyValue1(generic1);

showKeyValue1(generic2);
showKeyValue1(generic3);
showKeyValue1(generic4);
```
如果我们把泛型类的定义也改一下:
```java
public class Generic<T extends Number>{
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }
}
```
```java
//这一行代码也会报错，因为String不是Number的子类
Generic<String> generic1 = new Generic<String>("11111");
```
再来一个泛型方法的例子：
```java
//在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
//public <T> T showKeyName(Generic<T extends Number> container)，编译器会报错："Unexpected bound"
public <T extends Number> T showKeyName(Generic<T> container){
    System.out.println("container key :" + container.getKey());
    T test = container.getKey();
    return test;
}
```
<a name="gQ7gv"></a>
## 五、泛型的实际使用


<a name="dNBHD"></a>
### 5.1 利用泛型和反射抽象Dao


