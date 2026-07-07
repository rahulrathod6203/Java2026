package designPatterns.Singleton;

public class Database {

    private static Database INSTANCE ;

    private Database(){}

    public static synchronized Database getINSTANCE(){
        if( INSTANCE == null){
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

}
