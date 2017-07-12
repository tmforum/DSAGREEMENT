import com.amdocs.atin.Test;
import com.amdocs.renu.People;

/**
 * Created by atinsingh on 6/20/17.
 */
public class Main {

    public static void main(String []argv){

        People obj1 = new People();
        obj1.setAge(23);
        obj1.setName("Yahoo Singh");
        People obj2 = new People();

        obj1.printDetails();

    }
}

