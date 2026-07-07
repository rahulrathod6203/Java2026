package designPatterns.Singleton;

public class MainApp {

     static Database db1;
     static Database db2;

    public static void main(String[] args) throws InterruptedException {

        Thread t1= new Thread( ()->{
             db1= Database.getINSTANCE();

        });

        Thread t2= new Thread( ()->{
             db2= Database.getINSTANCE();

        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("db1==db2 : " + (db1==db2));
        System.out.println("db1 hashCode : " + db1.hashCode());
        System.out.println("db2 hashCode : " + db2.hashCode());


    }
}
