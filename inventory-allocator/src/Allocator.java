import java.util.HashMap;
import java.util.Scanner;

public class Allocator {

    public static void main(String []args){
        Scanner input = new Scanner(System.in);

        String map = input.nextLine();
        String storage = input.nextLine();

        map = map.substring(2);

        HashMap<String, Integer> find = new HashMap<String, Integer>();

        while(map.contains(":")){
            int colon = map.indexOf(':');
            String name = map.substring(0, colon);
            int amount = Integer.parseInt(map.substring(colon + 2, colon + 3));

            find.put(name, amount);
        }
    }
}
