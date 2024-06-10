
package lesson2;

import java.lang.reflect.*;

public class Reflections {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Person person = new Person("Igor");
        System.out.println(person);

        Class <? extends Person> aClass = Person.class;//person.getClass();
        Constructor <? extends Person> constructor = aClass.getConstructor(String.class);
        Person anotherPerson = constructor.newInstance("Igor");
        System.out.println(anotherPerson);

        Constructor<?>[] declareConstructors = aClass.getDeclaredConstructors();
        int i = 1;
        for (Constructor<?> declareConstructor : declareConstructors){
            System.out.println("Constructor #" + i++);
            for (Parameter parameter : declareConstructor.getParameters())
                System.out.println(parameter.getType());
        }

        System.out.println(Person.class.getDeclaredConstructors().length);
        System.out.println(Person.class.getConstructors().length);
        System.out.println();
        System.out.println(ExPerson.class.getDeclaredConstructors().length);
        System.out.println(ExPerson.class.getConstructors().length);

        person.setName("Updated");
        System.out.println(person);

        Method setName = Person.class.getMethod("setName", String.class);
        setName.invoke(person, "Updated #2");
        System.out.println(person);

        Method getName = Person.class.getMethod("getName");
        System.out.println(getName.invoke(person));

        System.out.println(Person.class.getMethod("getCounter").invoke(null));

        Method method = PrivateMethodHolder.class.getDeclaredMethod("method");
        method.setAccessible(true);
        method.invoke(new PrivateMethodHolder());

        Field name = Person.class.getDeclaredField("name");
        name.set(person, "New name");
        System.out.println(person);

        System.out.println(person.unmodifiable);

        Field unmodyfiable = Person.class.getDeclaredField("unmodifiable");
        unmodyfiable.setAccessible(true);
        unmodyfiable.set(person, "New final");
        System.out.println(person.unmodifiable);
    }

    private static class  ExPerson extends Person{
        public ExPerson(int age) {
        }

        private void foo(){}

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private static class Person {
        private static long counter = 0L;
        private String name;
        private final String unmodifiable;

        public Person(){
            this("unnamed");
        }

        public Person(String name){
            this.name = name;
            unmodifiable = "VALUE";
            counter++;
        }

        public static long getCounter() {
            return counter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
